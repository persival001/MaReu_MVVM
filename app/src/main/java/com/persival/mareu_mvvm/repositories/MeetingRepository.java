package com.persival.mareu_mvvm.repositories;

import androidx.annotation.NonNull;

import com.persival.mareu_mvvm.di.DI;
import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.service.MeetingApiService;

import java.util.ArrayList;
import java.util.List;

public class MeetingRepository {

    // Ne t'embête pas à avoir un Repository qui a un "ApiService" (ça veut même rien dire ApiService lol)
    private final MeetingApiService service = DI.getMeetingApiService();
    private static MeetingRepository INSTANCE;
    private final List<String> participants = new ArrayList<>();

    // Si tu voulais faire un singleton, il faut mettre son constructeur en privé comme ça, la seule façon de récupérer
    // une instance dans la classe, c'est via la fonction static getInstance()
    // Mais si tu le fais, tu vas avoir des problèmes pour injecter ton `AddMeetingViewModel`. Il faudra donc utiliser
    // une ViewModelFactory en Singleton pour gérer ton injection de dépendance. Et tu pourras aussi virer la classe DI
    // comme ça, elle fera doublon avec la ViewModelFactory !
    public MeetingRepository() {
    }

    public static MeetingRepository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MeetingRepository();
        }

        return INSTANCE;
    }

    /**
     * @return the Meeting list
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

    // Ca ne devrait pas être fait côté Repository ça, mais plutôt dans le ViewModel qui s'occupe d'afficher ces données
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

    // Ca ne devrait pas être fait côté Repository ça, mais plutôt dans le ViewModel qui s'occupe d'afficher ces données
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

    // Une petite enum ça serait mieux là :D
    // Attention, notion de "spinner" dans le Repository, le Repo n'a aucune connaissance de la View
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

    // A faire je présume ?
    public List<String> getParticipants() {
        return participants;
    }
}
