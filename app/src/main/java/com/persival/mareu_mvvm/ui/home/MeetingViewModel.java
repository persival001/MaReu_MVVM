package com.persival.mareu_mvvm.ui.home;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.repository.MeetingRepository;

import java.util.List;

public class MeetingViewModel extends ViewModel {

    private final MutableLiveData<List<Meeting>> meetingsMutableLiveData = new MutableLiveData<>();
    public LiveData<List<Meeting>> meetingsLiveData = meetingsMutableLiveData;

    /**
     * Gets meetings
     * 1 - With filterType null and filterValue null we get a complete list
     * 2 - With filterType room and filterValue number of room we get a filtered list by room
     * 3 - With filterType date and filterValue date we get a filtered list by date
     *
     * @param filterType  the filter type
     * @param filterValue the filter value
     */
    public void getMeetings(String filterType, String filterValue) {
        meetingsMutableLiveData.setValue(MeetingRepository.getInstance().getMeetings(filterType, filterValue));
    }


    public void onDeleteButtonClicked(long meetingId) {
        meetingsMutableLiveData.setValue(MeetingRepository.getInstance().deleteMeeting(meetingId));
    }

    /**
     * Gets room string for filterValue
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
