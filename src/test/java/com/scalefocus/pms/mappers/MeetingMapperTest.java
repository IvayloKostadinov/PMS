package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Meeting;
import com.scalefocus.pms.models.binding.MeetingBindingModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MeetingMapperTest {

    private MeetingBindingModel bindingModel;
    private MeetingMapper meetingMapper;
    private CycleAvoidingMappingContext context;


    @Before
    public void setUp() throws Exception {
        bindingModel = new MeetingBindingModel();
        meetingMapper = MeetingMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();

    }

    @Test
    public void meetingToModelTest1() {
        Meeting meeting = new Meeting();

        meeting.setNotes(TestConstants.TEST_STRING_MEETING_NOTES);


        bindingModel = meetingMapper.meetingToMeetingBindingModel(meeting, context);

        assertEquals(bindingModel.getNotes(), TestConstants.TEST_STRING_MEETING_NOTES);

    }




    @Test
    public void modelToMeetingTest() {
        bindingModel.setMeetingTheme(TestConstants.TEST_STRING);

        Meeting meeting = meetingMapper.meetingModelToMeeting(bindingModel, context);

        assertEquals(meeting.getAgenda(), TestConstants.TEST_STRING);
    }
}