package com.persival.mareu_mvvm.ui.home;

import com.persival.mareu_mvvm.model.Meeting;

// Tu peux l'appeler "OnMeetingDeletedListener", c'est plus classique
public interface EventsMeeting {

    // 1/ Documente le comportement voulu, pas son exécution. Ce n'est pas le rôle de l'interface de savoir où elle est
    // implémentée, au contraire même c'est pour ça qu'on l'utilise!
    // 2/ Il va falloir t'habituer à ne plus travailler avec ces modèles qu'on appelle Entity (le Meeting ici est une
    // Entity). Il faudrait plutôt travailler avec son id, qui est un primitif, ou encore d'autres solutions en Kotlin
    void onDelete(Meeting meeting);
}
