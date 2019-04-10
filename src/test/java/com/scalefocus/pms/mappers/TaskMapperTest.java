package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Task;
import com.scalefocus.pms.models.binding.TaskBindingModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TaskMapperTest {

    private TaskBindingModel bindingModel;
    private TaskMapper taskMapper;
    private CycleAvoidingMappingContext context;

    @Before
    public void setUp() throws Exception {
        bindingModel = new TaskBindingModel();
        taskMapper = TaskMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();
    }

    @Test
    public void taskToModelTest() {
        Task task = new Task();
        task.setDescription(TestConstants.TEST_STRING);

        bindingModel = taskMapper.taskToModel(task, context);

        assertEquals(bindingModel.getDescription(), TestConstants.TEST_STRING);
    }

    @Test
    public void modelToTaskTest() {
        bindingModel.setDescription(TestConstants.TEST_STRING);

        Task task = taskMapper.modelToTask(bindingModel, context);

        assertEquals(task.getDescription(), TestConstants.TEST_STRING);
    }
}