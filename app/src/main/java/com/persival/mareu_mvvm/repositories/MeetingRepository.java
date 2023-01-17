package com.persival.mareu_mvvm.repositories;

import com.persival.mareu_mvvm.di.DI;
import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {

    private static MeetingRepository INSTANCE;
    private final MeetingApiService service = DI.getMeetingApiService();
    private final List<String> participants = new ArrayList<>();

    public long maxId = 8;

    /**
     * Singleton for get instance of MeetingRepository
     */
    public MeetingRepository() {
    }

    public static MeetingRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MeetingRepository();
        }

        return INSTANCE;
    }

    /**
     * Return the meeting list
     */
    public List<Meeting> getMeetings(String filterType, String filterValue) {
        if (filterType == null) {
            return service.getMeetings();
        }
        if (filterType.equals("date")) {
            return filteredByDate(filterValue);
        }
        if (filterType.equals("room")) {
            return filteredByRoom(filterValue);
        }
        return service.getMeetings();
    }

    /**
     * Deletes a meeting.
     *
     * @param meeting the meeting
     */
    public List<Meeting> deleteMeeting(Meeting meeting) {
        service.deleteMeeting(meeting);
        return service.getMeetings();
    }

    /**
     * Add a meeting.
     */
    public void addMeeting(Meeting meeting) {
        service.addMeeting(meeting);
    }

    /**
     * Return the filtered list by date
     *
     * @param date the date
     * @return the list
     */

    public List<Meeting> filteredByDate(String date) {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> meetingListByDate = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getDate().equals(date)) {
                meetingListByDate.add(meeting);
            }
        }
        return meetingListByDate;
    }

    /**
     * Return the filtered list by room
     *
     * @param room the room
     * @return the list
     */
    public List<Meeting> filteredByRoom(String room) {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> meetingListByRoom = new ArrayList<>();
        for (Meeting meeting : meetings) {
            if (meeting.getMeetingRoom().equals(room)) {
                meetingListByRoom.add(meeting);
            }
        }
        return meetingListByRoom;
    }

    /**
     * Return the list of participants email
     */
    public List<String> getParticipants() {
        return participants;
    }

    public void addNewMeeting(
            String topic,
            String date,
            String startHour,
            String roomNumber,
            String emails
    ) {

        Meeting meeting = new Meeting(maxId++, topic, date, startHour, roomNumber, emails);
        addMeeting(meeting);
    }
}
