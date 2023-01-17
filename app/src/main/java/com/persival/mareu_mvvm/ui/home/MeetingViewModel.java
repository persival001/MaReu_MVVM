package com.persival.mareu_mvvm.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.repositories.MeetingRepository;

import java.util.List;

/**
 * The type Meeting view model.
 */
public class  MeetingViewModel extends ViewModel {

    private final MutableLiveData<List<Meeting>> meetingsMutableLiveData = new MutableLiveData<>();
    public LiveData<List<Meeting>> meetingsLiveData = meetingsMutableLiveData;

    /**
     * Gets meetings.
     *
     * @param filterType  the filter type
     * @param filterValue the filter value
     */
    public void getMeetings(String filterType, String filterValue) {
        meetingsMutableLiveData.setValue(MeetingRepository.getInstance().getMeetings(filterType, filterValue));
    }

    /**
     * Delete meeting.
     *
     * @param meeting the meeting
     */
    public void deleteMeeting(Meeting meeting) {
        meetingsMutableLiveData.setValue(MeetingRepository.getInstance().deleteMeeting(meeting));
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
