// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers;

import com.scalefocus.pms.constants.AttributeConstants;
import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.domain.Meeting;
import com.scalefocus.pms.models.binding.MeetingBindingModel;
import com.scalefocus.pms.services.MeetingService;
import com.scalefocus.pms.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

/**
 * This is the controller for Meeting page.
 */
@Controller
public class MeetingController extends BaseController {


    private final MeetingService meetingService;
    private final ProjectService projectService;

    /**
     * This is a constructor for autowiring all dependencies which MeetingService requires.
     */
    @Autowired
    public MeetingController(MeetingService meetingService, ProjectService projectService) {
        this.meetingService = meetingService;
        this.projectService = projectService;
    }

    /**
     * @return - This method returns page for creating new Meeting.
     */
    @GetMapping(MappingConstants.NEW_MEETING_FORM)
    public ModelAndView getMeetingForm(@PathVariable String projectId, ModelAndView model) {
        model.addObject(AttributeConstants.PROJECT, projectService.findModelById(Long.valueOf(projectId)));
        model.addObject(AttributeConstants.MEETING, new MeetingBindingModel());
        return this.view(ViewNameConstants.MEETING_CREATE, model);

    }

    /**
     * @param projectId - searched project.
     * @param model     - model and view.
     * @return - page for visualising all meetings for the searched project.
     */
    @GetMapping(MappingConstants.MEETINGS_GET_ALL)
    public ModelAndView getAllMeetings(@PathVariable String projectId, ModelAndView model) {
        model.addObject(AttributeConstants.PROJECT,
                projectService.findModelById(Long.valueOf(projectId)).getMeetings());
        return this.view(ViewNameConstants.ALL_MEETINGS_VIEW, model);
    }

    /**
     * This method check for no empty fields left in the form and if so create a meeting.
     *
     * @param bindingModel - Binding model for meeting creation.
     * @param model        - Model and View.
     * @return - if successfully competed form Meeting is created and user is redirected to the home page.
     */
    @PostMapping(MappingConstants.CREATE_MEETING)
    public ModelAndView createMeeting(@ModelAttribute MeetingBindingModel bindingModel,
                                      ModelAndView model) {

        Long projectIdforRedirect = bindingModel.getProject().getId();
        Meeting meeting = meetingService.registerMeeting(bindingModel);

        if (meeting == null) {
            model.addObject(AttributeConstants.ERROR, AttributeConstants.ERROR_REGISTER_MEETING);
            return this.view(ViewNameConstants.MEETING_CREATE, model);
        }

        return new ModelAndView(new RedirectView(MappingConstants.MEETINGS_GET_ALL
                .replace(AttributeConstants.DYNAMIC_PROJECT_ID, projectIdforRedirect.toString() )));
    }


}