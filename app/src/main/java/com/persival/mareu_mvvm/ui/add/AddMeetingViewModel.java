package com.persival.mareu_mvvm.ui.add;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.persival.mareu_mvvm.repositories.MeetingRepository;

import java.util.List;

public class AddMeetingViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isSaveButtonEnabled = new MutableLiveData<>(false);
    /**
     * LiveData for finish AddMeeting Activity, Default value is false
     */
    private final MutableLiveData<Void> isFinish = new MutableLiveData<>();

    /**
     * Need to close live data.
     *
     * @return the Boolean live data for close Activity
     */
    public LiveData<Void> needToClose() {
        return isFinish;
    }

    /**
     * Gets is save button enabled live data.
     *
     * @return the is save button enabled live data
     */
    public LiveData<Boolean> getIsSaveButtonEnabledLiveData() {
        return isSaveButtonEnabled;
    }

    /**
     * Set value isEmpty for Save button if participants is empty
     *
     * @param participants the participants
     */
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

        MeetingRepository.getInstance().addNewMeeting(
                topic,
                date,
                startHour,
                getRoomString(roomNumber),
                listOfEmailParticipantsToString()
        );
        isFinish.setValue(null);
    }

    /**
     * Gets participants.
     *
     * @return the list of emails participants
     */
    public List<String> getParticipants() {
        return MeetingRepository.getInstance().getParticipants();
    }

    /**
     * List of email participants to string
     *
     * @return the string
     */
    public String listOfEmailParticipantsToString() {
        String participantsString = "";
        for (int i = 0; i < getParticipants().size(); i++) {
            participantsString = participantsString + getParticipants().get(i) + "   ";
        }
        return participantsString;
    }

    /**
     * Gets room string.
     *
     * @param roomSpinner the room number
     * @return the room string
     */
    public String getRoomString(@NonNull String roomSpinner) {
        switch (roomSpinner) {
            case "Room One":
                return "1";
            case "Room Two":
                return "2";
            case "Room Three":
                return "3";
            case "Room Four":
                return "4";
            case "Room Five":
                return "5";
            case "Room Six":
                return "6";
            case "Room Seven":
                return "7";
            case "Room Eight":
                return "8";
            case "Room Nine":
                return "9";
            case "Room Ten":
                return "10";

            default:
                return "1";
        }
    }
}