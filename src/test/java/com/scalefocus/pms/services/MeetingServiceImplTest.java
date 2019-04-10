package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Meeting;
import com.scalefocus.pms.models.binding.MeetingBindingModel;
import com.scalefocus.pms.repositories.MeetingRepository;
import com.scalefocus.pms.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MeetingServiceImplTest {

    private MeetingService meetingService;
    @Mock
    private MeetingRepository meetingRepository;

    @Mock
    private UserRepository userRepository;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        meetingService = new MeetingServiceImpl(meetingRepository);

    }

   @Test
   public void registerMeeting() {
       MeetingBindingModel bindingModel = new MeetingBindingModel();
      bindingModel.setNotes(TestConstants.TEST_STRING);

        Meeting meeting = new Meeting();
        meeting.setNotes(TestConstants.TEST_STRING);

       when(this.userRepository.saveAndFlush(any())).thenReturn(meeting);

        Meeting savedMeeting = meetingService.registerMeeting(bindingModel);

        assertEquals(savedMeeting.getNotes(), bindingModel.getNotes());

    }
}