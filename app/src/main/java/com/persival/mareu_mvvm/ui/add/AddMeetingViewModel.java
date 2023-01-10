package com.persival.mareu_mvvm.ui.add;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.repositories.MeetingRepository;
import com.persival.mareu_mvvm.ui.home.MeetingViewModel;

import java.util.Objects;

public class AddMeetingViewModel extends ViewModel{

    private long maxId = 8;
    private final MeetingRepository meetingRepository;

    /**
     * Instantiates a new Add meeting view model.
     */
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

    /**
     * LiveData for finish AddMeetingActivity, Default value is false
     */
    private final MutableLiveData<Boolean> isFinish = new MutableLiveData<>(false);


    /**
     * Need to close live data.
     *
     * @return the live data
     */
    public LiveData<Boolean> needToClose() {
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
     * On participants changed.
     *
     * @param participants the participants
     */
    public void onParticipantsChanged(String participants) {
        isSaveButtonEnabled.setValue(!participants.isEmpty());
    }

    /**
     * On add button clicked.
     * Convert room selected to int.
     * Add "réunion" for default value to topic.
     * Add new meeting to repository.
     *
     * @param topic        the topic
     * @param date         the date
     * @param startHour    the start hour
     * @param roomNumber   the room number
     * @param participants the participants
     */
    public void onAddButtonClicked(
            String topic,
            String date,
            String startHour,
            String roomNumber,
            String participants
    ) {

          if (Objects.equals(topic, "")) {
             topic = "Réunion";
         }

        Meeting meeting = new Meeting(maxId++,topic, date, startHour,meetingRepository.getRoomString(roomNumber),participants);
        meetingRepository.addMeeting(meeting);
        isFinish.setValue(true);
    }
}