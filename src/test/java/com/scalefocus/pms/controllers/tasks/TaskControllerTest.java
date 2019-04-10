package com.scalefocus.pms.controllers.tasks;

import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.services.MeetingService;
import com.scalefocus.pms.services.PhaseService;
import com.scalefocus.pms.services.TaskService;
import com.scalefocus.pms.validators.TaskCreationFormValidator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TaskControllerTest {
    @Mock
    private TaskService taskService;
    @Mock
    private TaskCreationFormValidator taskCreationFormValidator;
    @Mock
    private MeetingService meetingService;
    @Mock
    private PhaseService phaseService;

    private MockMvc mockMvc;
    private TaskController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new TaskController(taskService, taskCreationFormValidator, meetingService,
                phaseService);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();

    }

    @Test
    public void getAllEligibleStatusesForTask() {
        int size = controller.getAllEligibleStatusesForTask().size();
        assertEquals(TestConstants.EXPECTED_SIZE_OF_STATUSES, size);
    }

    @Test
    public void testShowCreateTaskPageWithInvalidMeetingId() throws Exception {
        mockMvc.perform(get(TestConstants.NUMBER_TASK_100))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(MappingConstants.HOME));

    }


}