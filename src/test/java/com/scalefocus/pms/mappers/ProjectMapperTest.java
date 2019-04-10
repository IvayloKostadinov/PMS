package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Project;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProjectMapperTest {

    private ProjectBindingModel bindingModel;
    private ProjectMapper projectMapper;
    private CycleAvoidingMappingContext context;

    @Before
    public void setUp() throws Exception {
        bindingModel = new ProjectBindingModel();
        projectMapper = ProjectMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();
    }

    @Test
    public void projectToModelTest() {
        Project project = new Project();
        project.setProjectName(TestConstants.TEST_STRING);
        project.setBudget(TestConstants.TEST_DOUBLE);

        bindingModel = projectMapper.projectToProjectBindingModel(project, context);

        assertEquals(bindingModel.getProjectName(), TestConstants.TEST_STRING);
        assertEquals(bindingModel.getBudget(), TestConstants.TEST_DOUBLE);
    }

    @Test
    public void modelToProject() {
        bindingModel.setProjectName(TestConstants.TEST_STRING);
        bindingModel.setBudget(TestConstants.TEST_DOUBLE);

        Project project = projectMapper.projectModelToProject(bindingModel, context);

        assertEquals(project.getProjectName(), TestConstants.TEST_STRING);
        assertEquals(project.getBudget(), TestConstants.TEST_DOUBLE);
    }
}