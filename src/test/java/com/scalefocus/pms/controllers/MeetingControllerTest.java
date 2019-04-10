package com.scalefocus.pms.controllers;

import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.services.MeetingService;
import com.scalefocus.pms.services.ProjectService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class MeetingControllerTest {

    @Mock
    private MeetingService meetingService;
    @Mock
    private ProjectService projectService;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        MeetingController controller = new MeetingController(meetingService, projectService);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }


    @Test
    public void getMeetingForm() throws Exception {
        mockMvc.perform(get(TestConstants.NEW_MEETING_FORM_TEST))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNameConstants.MEETING_CREATE));
    }

    @Test
    public void createMeeting() throws Exception {
        mockMvc.perform(post(MappingConstants.CREATE_MEETING))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name(ViewNameConstants.MEETING_CREATE));
    }


}