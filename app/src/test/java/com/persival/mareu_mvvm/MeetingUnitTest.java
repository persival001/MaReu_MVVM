package com.persival.mareu_mvvm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.persival.mareu_mvvm.di.DI;
import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.repository.MeetingRepository;
import com.persival.mareu_mvvm.service.DummyMeetingGenerator;
import com.persival.mareu_mvvm.service.MeetingApiService;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Unit test on Meeting service
 */
public class MeetingUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    /**
     * Gets meeting with success.
     */
    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeeting = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeeting.toArray()));
    }

    /**
     * Delete meeting with success.
     */
    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        assertTrue(service.getMeetings().contains(meetingToDelete));
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    /**
     * Add meeting with success.
     */
    @Test
    public void addMeetingWithSuccess() {
        Meeting meeting = new Meeting(20, "Reunion", "12 FEV 2023", "10:00", "1", "jean@aol.com");
        assertFalse(service.getMeetings().contains(meeting));
        service.getMeetings().add(meeting);
        assertTrue(service.getMeetings().contains(meeting));
    }

    /**
     * Filter meeting by room with success.
     */
    @Test
    public void filterMeetingByRoomWithSuccess() {
        List<Meeting> getFilterMeetingByRoom = MeetingRepository.getInstance().getMeetings("room", "1");
        assertEquals(1, getFilterMeetingByRoom.size());
        assertEquals("1", getFilterMeetingByRoom.get(0).getMeetingRoom());
    }

    /**
     * Filter meeting by date with success.
     */
    @Test
    public void filterMeetingByDateWithSuccess() {
        List<Meeting> getFilterMeetingByDate = MeetingRepository.getInstance().getMeetings("date", "1 FEV 2023");
        assertEquals(2, getFilterMeetingByDate.size());
        assertEquals("1 FEV 2023", getFilterMeetingByDate.get(0).getDate());
        assertEquals("1 FEV 2023", getFilterMeetingByDate.get(1).getDate());
    }

    /**
     * Filter meeting by date with success.
     */
    @Test
    public void noFilteredListWithSuccess() {
        List<Meeting> getFilterMeetingByDate = MeetingRepository.getInstance().getMeetings(null, null);
        assertEquals(8, getFilterMeetingByDate.size());
    }
}