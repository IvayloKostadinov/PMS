// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.ValidationConstants;
import com.scalefocus.pms.domain.Task;
import com.scalefocus.pms.exceptions.TaskNotFoundException;
import com.scalefocus.pms.mappers.CycleAvoidingMappingContext;
import com.scalefocus.pms.mappers.TaskMapper;
import com.scalefocus.pms.models.binding.TaskBindingModel;
import com.scalefocus.pms.repositories.TasksRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

    private final CycleAvoidingMappingContext context;
    private final TaskMapper mapper;
    private final TasksRepository tasksRepository;

    /**
     * This is the constructor autowiring all of the dependencies.
     *
     * @param tasksRepository - Meeting Repository.
     */
    @Autowired
    public TaskServiceImpl(TasksRepository tasksRepository) {
        mapper = TaskMapper.INSTANCE;
        this.tasksRepository = tasksRepository;
        this.context = new CycleAvoidingMappingContext();

    }

    /**
     * The method saves task to the DB.
     * @param bindingModel - The model binding model for the Task entity
     * @return - true if the task is saved.
     */
    @Override
    public boolean addTask(final TaskBindingModel bindingModel) {

        final Task task = TaskMapper.INSTANCE.modelToTask(bindingModel, context);
        task.setProject(task.getMeeting().getProject());
        this.tasksRepository.saveAndFlush(task);

        return true;
    }

    /**
     * This method maps the task to the create binding model.
     * @param id -  id of the task to be mapped.
     * @return - returns task binding model.
     */
    @Override
    public TaskBindingModel getTaskBindingModelById(Long id) {
        Task task = this.tasksRepository.findById(id).orElse(null);
        if (task == null) {
            throw new TaskNotFoundException(ValidationConstants.TASK_NOT_FOUND);
        }
        return mapper.taskToModel(task, context);
    }

}
