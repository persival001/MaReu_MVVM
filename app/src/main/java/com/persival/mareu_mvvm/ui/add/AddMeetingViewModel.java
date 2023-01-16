package com.persival.mareu_mvvm.ui.add;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.repositories.MeetingRepository;

import java.util.List;
import java.util.Objects;

public class AddMeetingViewModel extends ViewModel {

    // Cette info je la verrai plutôt côté Repository, non ? :p
    private long maxId = 8;
    private final MeetingRepository meetingRepository;

    // Constructeur à supprimer absolument, il faut toujours injecter les dépendances, ce n'est pas le rôle du ViewModel
    // de savoir comment les récupérer
    public AddMeetingViewModel() {
        meetingRepository = new MeetingRepository();
    }

    public AddMeetingViewModel(@NonNull MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    /**
     * Save button is not enabled at start, Default value is false
     */
    private final MutableLiveData<Boolean> isSaveButtonEnabled = new MutableLiveData<>(false);

    // Tu as besoin d'exposer un événement ici, pas une data. Utilise donc une alternative comme le SingleLiveEvent
    // ou un EventWrapper
    // Et pour éviter de d'embêter à checker ton boolean dans la vue, tu peux simplement ne pas donner de valeur
    // initiale, et utiliser juste "Void" comme type pour dire qu'on s'en fiche de ce qui transite à l'intérieur de la
    // LiveData, c'est juste l'événement, le "ping", qui est intéressant
    private final MutableLiveData<Void> isFinish = new MutableLiveData<>();

    public LiveData<Void> needToClose() {
        return isFinish;
    }

    public LiveData<Boolean> getIsSaveButtonEnabledLiveData() {
        return isSaveButtonEnabled;
    }

    // Paramètre non utilisé
    public void onParticipantsChanged(String participants) {
        isSaveButtonEnabled.setValue(getParticipants().size() > 0);
    }

    /**
     * On add button clicked.
     * Convert room selected to int.
     * Add "réunion" for default value to topic.
     * Add new meeting to repository.
     *
     * @param topic      the topic
     * @param date       the date
     * @param startHour  the start hour
     * @param roomNumber the room number
     */
    public void onAddButtonClicked(
        String topic,
        String date,
        String startHour,
        String roomNumber
    ) {

        if (Objects.equals(topic, "")) {
            // Et nos potes outre Manche, faut y penser!
            topic = "Réunion";
        }

        // Ce n'est pas au ViewModel de créer le Meeting (surtout qu'il y a une notion d'id incrémental), donc laisse ça
        // au Repository plutôt !
        Meeting meeting = new Meeting(
            maxId++,
            topic,
            date,
            startHour,
            meetingRepository.getRoomString(roomNumber),
            listOfEmailParticipantsToString()
        );
        meetingRepository.addMeeting(meeting);
        isFinish.setValue(null);
    }

    // Jamais un ViewModel ne doit retourner une donnée, à part le getter de la LiveData de ton Model de View
    // (ViewState ou UiModel, c'est la même chose). C'est pour ça qu'il y a autant d'intelligence côté View. Passe cette
    // fonction en privée et essaye de bouger toute l'intelligence depuis la View jusqu'ici.
    public List<String> getParticipants() {
        return meetingRepository.getParticipants();
    }

    public String listOfEmailParticipantsToString() {
        String participantsString = "";
        for (int i = 0; i < getParticipants().size(); i++) {
            participantsString = participantsString + getParticipants().get(i) + "   ";
        }
        return participantsString;
    }
}