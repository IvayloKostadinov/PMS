package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.FieldConstants;
import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Phase;
import com.scalefocus.pms.domain.Project;
import com.scalefocus.pms.models.binding.PhaseBindingModel;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.repositories.ProjectRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class ProjectServiceImplTest {
    private ProjectService projectService;

    @Mock
    private ProjectRepository projectRepository;
    @Mock
    private PhaseService phaseService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        projectService = new ProjectServiceImpl(projectRepository, phaseService);
    }

    @Test
    public void createProjectTest() {
        ProjectBindingModel bindingModel = new ProjectBindingModel();
        PhaseBindingModel phaseBindingModel = new PhaseBindingModel();

        bindingModel.setBudget(FieldConstants.BINDING_MODEL_DOUBLE);
        bindingModel.setDescription(FieldConstants.BINDING_MODEL_STRING);
        bindingModel.setProjectName(FieldConstants.BINDING_MODEL_STRING);
        phaseBindingModel.setPhaseName(FieldConstants.BINDING_MODEL_STRING);

        when(phaseService.createPhase(phaseBindingModel)).thenReturn(new Phase());

        Project project = projectService.registerProject(bindingModel, phaseBindingModel);

        assertEquals(project.getProjectName(), FieldConstants.BINDING_MODEL_STRING);
    }

    @Test
    public void findModelByIdSuccessfulTest() {
        Project project = new Project();
        project.setProjectName(TestConstants.PROJECT_NAME);

        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));

        ProjectBindingModel bindingModel = projectService.findModelById(String.valueOf(anyLong()));

        assertEquals(bindingModel.getProjectName(), TestConstants.PROJECT_NAME);
    }

    @Test
    public void getAllProjectsTest() {
        ProjectBindingModel project = new ProjectBindingModel();
        List<ProjectBindingModel> projectsData = new ArrayList<>();
        projectsData.add(project);

        when(projectService.getAllProjects()).thenReturn(projectsData);

        List<ProjectBindingModel> projects = projectService.getAllProjects();

        assertEquals(projects.size(), 1);
        verify(projectRepository, times(1)).findAll();
        verify(projectRepository, never()).findById(anyLong());
    }

    @Test
    public void deleteProjectSuccessfulTest() {
        projectService.deleteById(anyLong());
        verify(projectRepository, times(1)).deleteById(anyLong());
    }

    @Test
    public void findAllPhaseModelsByProjectId() {
        Project project = new Project();
        List<Phase> phases = new ArrayList<>();
        phases.add(new Phase());
        project.setPhases(phases);

        when(projectRepository.findById(anyLong())).thenReturn(Optional.of(project));

        List<PhaseBindingModel> bindingModels = projectService.findAllPhaseModelsByProjectId(String.valueOf(anyLong()));

        assertEquals(bindingModels.size(), 1);
    }
}