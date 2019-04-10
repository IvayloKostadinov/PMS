// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.tasks;

import com.scalefocus.pms.constants.AttributeConstants;
import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.controllers.BaseController;
import com.scalefocus.pms.models.binding.MeetingBindingModel;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.models.binding.TaskBindingModel;
import com.scalefocus.pms.services.MeetingService;
import com.scalefocus.pms.services.PhaseService;
import com.scalefocus.pms.services.TaskService;
import com.scalefocus.pms.validators.TaskCreationFormValidator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

/**
 * Controller for task functionality.
 */
@Controller
public class TaskController extends BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
    private static final String STATUSES = "statuses";
    private static final String PHASES = "phases";
    private static final String USERS = "users";
    private static final String STATUS_IN_PROGRESS = "In Progress";
    private static final String STATUS_OPEN = "Open";
    private static final String STATUS_CLOSED = "Closed";
    private static final String STATUS_TO_BE_TESTED = "To be tested";
    private static final String PROJECT_ID = "projectId";
    private static final String CREATE_TASK_FORM = "createTaskForm";
    private static final String INITIAL_STEP_OF_INCREMENTING_TASK_NUMBER = ".1";
    private static final String TASK_NUMBER_SEPARATOR = ".";
    private static final int INDEX_INCREMENTATION_STEP = 1;
    private static final String DYNAMIC_PROJECT_ID = "{id}";


    private final TaskService taskService;
    private final TaskCreationFormValidator taskCreationFormValidator;
    private final MeetingService meetingService;
    private final PhaseService phaseService;

    /**
     * This is the constructor autowiring all the dependencies needed.
     * @param taskService - The service class for the Task entity.
     * @param taskCreationFormValidator - validate if the dates are correct.
     * @param meetingService - The service class for the Meeting entity.
     * @param phaseService - The service class for the Phase entity.
     */
    @Autowired
    public TaskController(TaskService taskService, TaskCreationFormValidator taskCreationFormValidator,
                          MeetingService meetingService, PhaseService phaseService) {
        this.taskService = taskService;
        this.taskCreationFormValidator = taskCreationFormValidator;
        this.meetingService = meetingService;
        this.phaseService = phaseService;
    }

    /**
     * Getting all valid statuses for the current task.
     */
    @ModelAttribute(STATUSES)
    public Map<Integer, String> getAllEligibleStatusesForTask() {
        final Map<Integer, String> statuses = new HashMap<>();
        statuses.put(1, STATUS_IN_PROGRESS);
        statuses.put(2, STATUS_OPEN);
        statuses.put(3, STATUS_CLOSED);
        statuses.put(4, STATUS_TO_BE_TESTED);
        return statuses;
    }

    /**
     * Get the form for creating a task.
     *
     * @param id - is PathVariable represents the current meeting.
     * @return view for creating task.
     */
    @GetMapping(MappingConstants.NEW_TASK_FORM)
    public ModelAndView showCreateTaskPage(@PathVariable Long id, final ModelAndView modelAndView) {

        final MeetingBindingModel meetingBindingModel = meetingService.getMeetingById(id);
        if (meetingBindingModel == null) {
            LOGGER.info("MeetingBindingModel is null. Redirecting to Home page. ");
            return new ModelAndView(new RedirectView(MappingConstants.HOME));
        }
        final TaskBindingModel taskBindingModel = new TaskBindingModel();
        final ProjectBindingModel projectBindingModel = meetingBindingModel.getProject();
        taskBindingModel.setMeeting(meetingBindingModel);
        generateTaskNumber(meetingBindingModel, taskBindingModel);
        modelAndView.addObject(PHASES, phaseService.getAvailablePhasesByProjectId(projectBindingModel));
        modelAndView.addObject(USERS, meetingBindingModel.getProject().getTeam().getUsers());
        modelAndView.addObject(PROJECT_ID, meetingBindingModel.getProject().getId());
        modelAndView.addObject(CREATE_TASK_FORM, taskBindingModel);
        modelAndView.setViewName(ViewNameConstants.ADD_NEW_TASK);
        return modelAndView;
    }

    /**
     * Post method for save the new task.
     *
     * @param createTaskForm gets the populated fields from BE.
     * @param bindingResult  - result.
     * @return save the new task.
     */
    @PostMapping(MappingConstants.ADD_NEW_TASK)
    public ModelAndView saveNewTask(final @Valid @ModelAttribute(CREATE_TASK_FORM) TaskBindingModel createTaskForm,
                                    final BindingResult bindingResult) {
        Long taskProjectId = createTaskForm.getMeeting().getProject().getId();
        final ModelAndView modelAndView = new ModelAndView();

        taskCreationFormValidator.validate(createTaskForm, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject(CREATE_TASK_FORM, createTaskForm);
            modelAndView.addObject(PROJECT_ID, taskProjectId);
            modelAndView.addObject(PHASES, phaseService
                    .getAvailablePhasesByProjectId(createTaskForm.getMeeting().getProject()));
            modelAndView.addObject(USERS, createTaskForm.getMeeting().getProject().getTeam().getUsers());
            return this.view(ViewNameConstants.ADD_NEW_TASK, modelAndView);
        }
        taskService.addTask(createTaskForm);

//        return new ModelAndView(new RedirectView(MappingConstants.SHOW_ALL_MEETING_TASKS
//                .replace(DYNAMIC_PROJECT_ID, taskProjectId.toString())));
        return  new ModelAndView( new RedirectView("/meeting/"+ createTaskForm.getMeeting().getId() + "/tasks"));
    }


    @GetMapping(MappingConstants.SHOW_ALL_MEETING_TASKS)
    public ModelAndView showAllTasks(@PathVariable String id,ModelAndView modelAndView){
        modelAndView.addObject(AttributeConstants.TASKS,meetingService.getMeetingById(Long.valueOf(id)).getTasks());
        return view(ViewNameConstants.ALL_TASKS,modelAndView);

    }
    /**
     * Helping method who generate task number.
     * Task number is generating from meeting number + number of the next task.
     *
     * @param meetingBindingModel gets Binding model for entity Meeting.
     * @param taskBindingModel    set the new number to task.
     */
    private void generateTaskNumber(final MeetingBindingModel meetingBindingModel,
                                    final TaskBindingModel taskBindingModel) {

        /**
         *     Adding .1 for the first initial step to task number.
         */
        if (CollectionUtils.isEmpty(meetingBindingModel.getTasks())) {
            taskBindingModel.setTaskNumber(meetingBindingModel.getId() + INITIAL_STEP_OF_INCREMENTING_TASK_NUMBER);
        }
        Optional<TaskBindingModel> biggestTaskNumber = meetingBindingModel.getTasks().stream()
                .sorted(Comparator.comparing(TaskBindingModel::getTaskNumber).reversed()).findFirst();

        if (biggestTaskNumber.isPresent()) {
            final String newNumberAsString = biggestTaskNumber.get().getTaskNumber();
            final String lastTaskNumber = newNumberAsString.substring(newNumberAsString
                    .lastIndexOf(TASK_NUMBER_SEPARATOR) + INDEX_INCREMENTATION_STEP);
            int newNumber = Integer.parseInt(lastTaskNumber);

            taskBindingModel.setTaskNumber(meetingBindingModel.getId() + TASK_NUMBER_SEPARATOR + ++newNumber);
        }
    }
}
