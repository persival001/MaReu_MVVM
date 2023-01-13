package com.persival.mareu_mvvm.service;

import com.persival.mareu_mvvm.model.Meeting;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyMeetingApiService implements MeetingApiService {

    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    /**
     * Api Service get Fake Meeting List
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Api Service deleteMeeting
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * Api Service addMeeting
     */
    @Override
    public void addMeeting(Meeting meeting) { meetings.add(meeting); }

}
