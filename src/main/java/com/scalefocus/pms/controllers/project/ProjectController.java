// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.project;

import com.scalefocus.pms.constants.AttributeConstants;
import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.controllers.BaseController;
import com.scalefocus.pms.domain.Project;
import com.scalefocus.pms.models.binding.PhaseBindingModel;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.services.ProjectService;
import com.scalefocus.pms.services.TeamService;
import com.scalefocus.pms.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class ProjectController extends BaseController {

    private final ProjectService projectService;
    private final UserService userService;
    private final TeamService teamService;

    /**
     * This is the constructor autowiring all the dependencies needed.
     *
     * @param projectService - The service class for the Project entity.
     * @param userService    - The service class for the User entity.
     * @param teamService    - The service class for the Team entity.
     */
    @Autowired
    public ProjectController(ProjectService projectService, UserService userService,
                             TeamService teamService) {
        this.projectService = projectService;
        this.userService = userService;
        this.teamService = teamService;
    }

    /**
     * This method gets the form for creating projects
     *
     * @param model - Model and View for the page.
     * @return - returns the view for creating projects
     */
    @GetMapping(MappingConstants.NEW_PROJECT_FORM)
    public ModelAndView getProjectForm(ModelAndView model) {

        model.addObject(AttributeConstants.MANAGERS,
                userService.getUserBindingModeByRole(AttributeConstants.MANAGER_ROLE));
        model.addObject(AttributeConstants.TEAMS, teamService.getAllTeamsBindingModels());
        model.addObject(AttributeConstants.PROJECT_MODEL, new ProjectBindingModel());
        model.addObject(AttributeConstants.PHASE_MODEL, new PhaseBindingModel());
        return this.view(ViewNameConstants.PROJECT_CREATE, model);
    }


    /**
     * This method registries the project
     *
     * @param bindingModel - The Binding Model for the Project
     * @param model        - Model and View for the page.
     * @return - returns redirected view to all projects
     */
    @PostMapping(MappingConstants.CREATE_PROJECT)
    public ModelAndView createProject(@ModelAttribute @Valid ProjectBindingModel bindingModel, BindingResult result,
                                      PhaseBindingModel phaseBindingModel,
                                      ModelAndView model) {

        if (result.hasErrors()) {
            model.addObject(AttributeConstants.MANAGERS,
                    userService.getUserBindingModeByRole(AttributeConstants.MANAGER_ROLE));
            model.addObject(AttributeConstants.TEAMS, teamService.getAllTeamsBindingModels());
            model.addObject(AttributeConstants.PROJECT_MODEL, new ProjectBindingModel());
            model.addObject(AttributeConstants.PHASE_MODEL, new PhaseBindingModel());
            model.addObject(AttributeConstants.ERROR, AttributeConstants.ERROR_REGISTER_PROJECT);
            return this.view(ViewNameConstants.PROJECT_CREATE, model);
        }

        projectService.registerProject(bindingModel, phaseBindingModel);

        return this.redirect(ViewNameConstants.PROJECTS);
    }

    /**
     * @param model - Model and View for the page.
     * @return - returns the view for all projects.
     */
    @RequestMapping(MappingConstants.PROJECTS)
    public ModelAndView getAllProjects(ModelAndView model) {
        model.addObject(AttributeConstants.PROJECTS, projectService.getAllProjects());
        model.addObject(AttributeConstants.PUBLIC_PROJECTS, projectService.getAllPublicProjects());
        return this.view(ViewNameConstants.PROJECTS, model);
    }

    /**
     * This method gets the form for creating projects
     *
     * @param model - Model and View for the page.
     * @return - returns the view for creating projects
     */
    @GetMapping(MappingConstants.UPDATE_PROJECT_FORM)
    public ModelAndView updateProjectForm(@PathVariable String id, ModelAndView model) {

        model.addObject(AttributeConstants.MANAGERS,
                userService.getUserBindingModeByRole(AttributeConstants.MANAGER_ROLE));
        model.addObject(AttributeConstants.TEAMS, teamService.getAllTeamsBindingModels());
        ProjectBindingModel bindingModel = projectService.findModelById(id);
        model.addObject(AttributeConstants.PROJECT_MODEL, projectService.findModelById(id));
        model.addObject(AttributeConstants.PHASES_MODEL, projectService.findAllPhaseModelsByProjectId(id));
        model.addObject(AttributeConstants.PHASE_MODEL, new PhaseBindingModel());
        return this.view(ViewNameConstants.PROJECT_UPDATE, model);
    }

    /**
     * This method registries the project
     *
     * @param bindingModel - The Binding Model for the Project
     * @param model        - Model and View for the page.
     * @return - returns redirected view to all projects
     */
    @PostMapping(MappingConstants.UPDATE_PROJECT)
    public ModelAndView updateProject(@ModelAttribute ProjectBindingModel bindingModel,
                                      PhaseBindingModel phaseBindingModel,
                                      ModelAndView model) {

        Project project = projectService.registerProject(bindingModel, phaseBindingModel);

        if (project == null) {
            model.addObject(AttributeConstants.ERROR, AttributeConstants.ERROR_UPDATE_PROJECT);
            return this.view(ViewNameConstants.PROJECT_UPDATE, model);
        }

        return this.redirect(ViewNameConstants.PROJECTS);
    }

    /**
     * This method deletes project by id.
     *
     * @param id - Path variable for project id.
     * @return - returns redirected view to all projects
     */
    @GetMapping(MappingConstants.DELETE_PROJECT)
    public ModelAndView deleteProject(@PathVariable String id) {
        projectService.deleteById(Long.valueOf(id));
        return this.redirect(ViewNameConstants.PROJECTS);
    }

    @GetMapping(MappingConstants.PROJECT)
    public ModelAndView showProject(@PathVariable String id, ModelAndView model){
        model.addObject("project", projectService.findModelById(id));

        return this.view(ViewNameConstants.PROJECT,model);
    }
}
