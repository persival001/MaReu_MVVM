package com.persival.mareu_mvvm.repositories;

import androidx.annotation.NonNull;

import com.persival.mareu_mvvm.di.DI;
import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

public class MeetingRepository{

    private final MeetingApiService service = DI.getMeetingApiService();
    private static MeetingRepository INSTANCE;
    private final List<String> participants = new ArrayList<>();

    /**
     * A Singleton can only be instantiated once
     *
     * New instance of MeetingRepository if null
     * or getInstance if non-null
     */
    public MeetingRepository() { }

    public static MeetingRepository getInstance() {
        if (INSTANCE == null)
            INSTANCE = new MeetingRepository();

        return INSTANCE;
    }

    /**
     * Get example meetings list.
     *
     * @return the list
     */
    public List<Meeting> getMeetings(String filterType, String filterValue){
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
     * Delete meeting.
     *
     * @param meeting the meeting
     */
    public List<Meeting> deleteMeeting(Meeting meeting){
        service.deleteMeeting(meeting);
        return service.getMeetings();
    }

    /**
     * Add meeting.
     *
     */
    public void addMeeting(Meeting meeting) {
        service.addMeeting(meeting);
    }

    /**
     * Filtered by date list.
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
     * Filtered by room list.
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
     * Gets room string.
     *
     * @param roomSpinner the room number
     * @return the room string
     */
    public String getRoomString(@NonNull String roomSpinner) {
        switch(roomSpinner) {
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

    public List<String> getParticipants(){
        return participants;
    }
}
