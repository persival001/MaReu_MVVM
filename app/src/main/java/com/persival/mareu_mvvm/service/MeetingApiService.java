package com.persival.mareu_mvvm.service;

import com.persival.mareu_mvvm.model.Meeting;

import java.util.List;

/**
 * Meeting API client
 */
public interface MeetingApiService {
    /**
     * Get all my Meetings
     *
     * @return {@link List}
     */
    List<Meeting> getMeetings();

    /**
     * Delete a meeting
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Add meeting.
     *
     * @param meeting the meeting
     */
    void addMeeting(Meeting meeting);
}
