package com.persival.mareu_mvvm;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.persival.mareu_mvvm.di.DI;
import com.persival.mareu_mvvm.model.Meeting;
import com.persival.mareu_mvvm.repositories.MeetingRepository;
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
            service.deleteMeeting(meetingToDelete);
            assertFalse(service.getMeetings().contains(meetingToDelete));
        }

        /**
         * Add meeting with success.
         */
        @Test
        public void addMeetingWithSuccess() {
            Meeting meetingToAdd = service.getMeetings().get(0);
            service.addMeeting(meetingToAdd);
            assertTrue(service.getMeetings().contains(meetingToAdd));
        }

        /**
         * Filter meeting by room with success.
         */
        @Test
        public void filterMeetingByRoomWithSuccess() {
            List <Meeting> getFilterMeetingByRoom = MeetingRepository.getInstance().getMeetings("room", "1");
            assertEquals(1, getFilterMeetingByRoom.size());
        }

        /**
         * Filter meeting by date with success.
         */
        @Test
        public void filterMeetingByDateWithSuccess() {
            List <Meeting> getFilterMeetingByDate = MeetingRepository.getInstance().getMeetings("date", "1 FEV 2023");
            assertEquals(2, getFilterMeetingByDate.size());
        }
    }