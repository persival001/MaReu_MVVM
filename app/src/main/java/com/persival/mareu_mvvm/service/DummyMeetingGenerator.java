package com.persival.mareu_mvvm.service;

import com.persival.mareu_mvvm.model.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(1,
                    "Logistique",
                    "1 FEV 2023",
                    "10:00",
                    "1",
                    "roberta@lamzone.com - louisette@lamzone.com"),
            new Meeting(2,
                    "Export",
                    "2 FEV 2023",
                    "8:00",
                    "5",
                    "jocelyne@lamzone.com - mireille@lamzone.com"),
            new Meeting(3,
                    "Emballage",
                    "3 FEV 2023",
                    "9:00",
                    "9",
                    "christelle@lamzone.com - marielle@lamzone.com"),
            new Meeting(4,
                    "Distribution",
                    "4 FEV 2023",
                    "13:00",
                    "8",
                    "rosa@lamzone.com - danie@lamzone.com"),
            new Meeting(5,
                    "Rapport",
                    "5 FEV 2023",
                    "14:00",
                    "7",
                    "cecilia@lamzone.com - jevalide@lamzone.com"),
            new Meeting(6,
                    "Environnement",
                    "6 FEV 2023",
                    "15:00",
                    "6",
                    "marie-louise@lamzone.com - berthe@lamzone.com"),
            new Meeting(7,
                    "Santé",
                    "7 FEV 2023",
                    "8:00",
                    "10",
                    "georgette@lamzone.com - louise@lamzone.com"),
            new Meeting(8,
                    "Horaires",
                    "8 FEV 2023",
                    "10:00",
                    "4",
                    "jaqueline@lamzone.com - danielle@lamzone.com - raymonde@lamzone.com - lucienne@lamzone.com")
    );
    private String topic;
    private String date;
    private String startHour;
    private String meetingRoom;
    private String mails;

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}
