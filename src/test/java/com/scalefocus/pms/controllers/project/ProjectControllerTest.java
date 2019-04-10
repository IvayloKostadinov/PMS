package com.scalefocus.pms.controllers.project;

import com.scalefocus.pms.constants.AttributeConstants;
import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.domain.Project;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.services.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class ProjectControllerTest {
    @Mock
    private ProjectService projectService;
    @Mock
    private UserService userService;
    @Mock
    private TeamService teamService;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ProjectController controller = new ProjectController(projectService,userService,teamService);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void getProjectFormTest() throws Exception {
        mockMvc.perform(get(MappingConstants.NEW_PROJECT_FORM))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNameConstants.PROJECT_CREATE));
    }

    @Test
    public void createProjectTest() throws Exception {
        mockMvc.perform(post(MappingConstants.CREATE_PROJECT))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name( ViewNameConstants.PROJECT_CREATE));
    }

    @Test
    public void getAllProjectsTestSuccessfulTest() throws Exception{
        mockMvc.perform(get(MappingConstants.PROJECTS))
                .andExpect(view().name(ViewNameConstants.PROJECTS))
                .andExpect(model().attributeExists(AttributeConstants.PROJECTS));

        verify(projectService, times(1)).getAllProjects();
    }

    @Test
    public void updateProjectExpectingResultToBeNullTest() throws Exception {
        when(projectService.registerProject(any(), any())).thenReturn(null);

        mockMvc.perform(post(MappingConstants.UPDATE_PROJECT))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name(ViewNameConstants.PROJECT_UPDATE));
    }

    @Test
    public void updateProjectSuccessfulTest() throws Exception {
        when(projectService.registerProject(any(), any())).thenReturn(new Project());

        mockMvc.perform(post(MappingConstants.UPDATE_PROJECT))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(ViewNameConstants.REDIRECT + ViewNameConstants.PROJECTS));
    }

    @Test
    public void updateProjectFormSuccessfulTest() throws Exception {
        ProjectBindingModel bindingModel = new ProjectBindingModel();
        when(projectService.findModelById(anyString())).thenReturn(bindingModel);

        mockMvc.perform(get(TestConstants.TEST_UPDATE_PROJECT))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNameConstants.PROJECT_UPDATE))
                .andExpect(model().attributeExists(AttributeConstants.MANAGERS))
                .andExpect(model().attributeExists(AttributeConstants.PROJECT_MODEL))
                .andExpect(model().attributeExists(AttributeConstants.PHASES_MODEL))
                .andExpect(model().attributeExists(AttributeConstants.PHASE_MODEL))
                .andExpect(model().attributeExists(AttributeConstants.TEAMS));
    }

    @Test
    public void deleteProjectSuccessfulTest() throws Exception {
        mockMvc.perform(get(TestConstants.TEST_DELETE_PROJECT))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(ViewNameConstants.REDIRECT +ViewNameConstants.PROJECTS));

        verify(projectService, times(1)).deleteById(anyLong());
    }
}