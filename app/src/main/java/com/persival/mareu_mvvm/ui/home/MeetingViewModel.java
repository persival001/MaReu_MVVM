package com.persival.mareu_mvvm.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.repositories.MeetingRepository;

import java.util.List;

// Ce ViewModel est partagé entre plusieurs Views (View au sens architectural du terme, donc en Android ça correspond
// à une Activity ou un Fragment). C'est une très mauvaise pratique. L'idée c'est d'avoir un seul ViewModel pour une
// seule View, et une View n'utilise qu'un seul ViewModel. Beaucoup plus simple comme ça. Là si tu changes ton écran de
// sélection de salle, ça peut potentiellement impacter ton écran d'affichage de meetings! Dangereux :p
public class  MeetingViewModel extends ViewModel {

    // Cette MutableLiveData qui représente ta "source de donnée" devrait se trouver dans le Repository. Mais comment
    // faire pour modifier la liste (notamment si on veut filtrer la liste de Meeting) ? Il faut utiliser un
    // Transformations.switchMap ou un MediatorLiveData pour combiner 2 LiveData: celle qui vient du Repository et qui
    // donne la liste complète des Meetings, et une autre MutableLiveData qui sera dans ton MeetingViewModel qui
    // représentera le type de tri. Grâce à ça, tu seras capable de naturellement réagir tant au changement de filtre
    // qu'à l'ajout d'un nouveau Meeting. C'est la force de la programmation réactive :D
    // Là, si tu rajoutes un Meeting alors que tu as déjà une méthode de tri, ça ne va pas être pris en compte
    private final MutableLiveData<List<Meeting>> meetingsMutableLiveData = new MutableLiveData<>();

    // Un peu "Kotlin style", mais c'est pas une mauvaise chose ^^
    // On aurait plutôt fait un getter qu'une property public en Java.
    public LiveData<List<Meeting>> meetingsLiveData = meetingsMutableLiveData;

    // 1/ Documente le comportement voulu, pas son exécution. Ici, on pourrait expliquer qu'il y a plusieurs façons de
    // filtrer les meetings (avec telle ou telle "clef" par exemple, comme "date" ou "room").
    // 2/ A part les getters des LiveData, toutes les fonctions publique d'un ViewModel doivent commencer par "on",
    // genre "onFilterChanged" par exemple ici. Quand on y pense, le ViewModel est là pour interpréter les événements
    // sur la vue. Donc les différentes fonctions vont toujours commencer par "on". Et côté TUs, ça fera beaucoup plus
    // sens!
    // 3/ Ici plutôt qu'une String filterType, tu pourrais utiliser un enum! Ou sinon avoir 2 fonctions, une pour la
    // date, et une pour la room ?
    public void getMeetings(String filterType, String filterValue) {
        // Injecte ton MeetingRepository dans ton constructeur de ViewModel plutôt que d'y accéder via un Singleton,
        // ça te permettra de tester unitairement cette classe, et c'est une meilleure pratique de laisser le choix
        // à ta DI de te donner telle ou telle instance de Repository. En tant que ViewModel, c'est pas ton rôle de
        // savoir ça.
        meetingsMutableLiveData.setValue(MeetingRepository.getInstance().getMeetings(filterType, filterValue));
    }

    // Plutôt "onDeleteButtonClicked" ou un truc du genre :)
    public void deleteMeeting(Meeting meeting) {
        meetingsMutableLiveData.setValue(MeetingRepository.getInstance().deleteMeeting(meeting));
    }

    // Jamais un ViewModel ne doit retourner une donnée, à part le getter de la LiveData de ton Model de View
    // (ViewState ou UiModel, c'est la même chose). Cette donnée (la "sélection de la salle") devrait rester à
    // l'intérieur de ton ViewModel de "SpinnerDialogFragment"
    public String getRoomString(String roomSpinner) {
        return MeetingRepository.getInstance().getRoomString(roomSpinner);
    }
}
