package com.persival.mareu_mvvm.ui.home;

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
     * @param roomSpinner the room spinner
     * @return the room string
     */
    public String getRoomString(String roomSpinner) {
        return MeetingRepository.getInstance().getRoomString(roomSpinner);
    }
}
