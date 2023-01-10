package com.persival.mareu_mvvm.model;

import java.util.Objects;

public class Meeting {

    private long id;
    private String topic;
    private String date;
    private String startHour;
    private String meetingRoom;
    private String mails;

    public Meeting(long id, String topic, String date, String startHour, String meetingRoom, String mails) {
        this.id = id;
        this.topic = topic;
        this.date = date;
        this.startHour = startHour;
        this.meetingRoom = meetingRoom;
        this.mails = mails;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(String meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public String getMails() {
        return mails;
    }

    public void setMails(String mails) { this.mails = mails;
    }

    public String getMeetingItemDisplay () {
        return topic + " Le " + date + " à " + startHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
