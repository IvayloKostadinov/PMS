// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.ValidationConstants;
import com.scalefocus.pms.domain.Phase;
import com.scalefocus.pms.domain.Project;
import com.scalefocus.pms.exceptions.BadRequestException;
import com.scalefocus.pms.exceptions.ProjectNotFoundException;
import com.scalefocus.pms.mappers.CycleAvoidingMappingContext;
import com.scalefocus.pms.mappers.PhaseMapper;
import com.scalefocus.pms.mappers.ProjectMapper;
import com.scalefocus.pms.models.binding.PhaseBindingModel;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.repositories.ProjectRepository;

import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper mapper;
    private final ProjectRepository projectRepository;
    private final PhaseService phaseService;
    private final PhaseMapper phaseMapper;
    private final CycleAvoidingMappingContext context;

    /**
     * This is the constructor autowiring all of the dependencies.
     *
     * @param projectRepository - Project repository
     * @param phaseService      - Phase service
     */
    @Autowired
    public ProjectServiceImpl(ProjectRepository projectRepository, PhaseService phaseService) {
        this.projectRepository = projectRepository;
        this.phaseService = phaseService;
        this.phaseMapper = PhaseMapper.INSTANCE;
        this.mapper = ProjectMapper.INSTANCE;
        this.context = new CycleAvoidingMappingContext();
    }

    /**
     * This method registers a project.
     *
     * @param bindingModel      - The model binding model for the Project entity
     * @param phaseBindingModel - The phase binding model
     * @return - Returns a project if it has been created
     */
    @Override
    public Project registerProject(ProjectBindingModel bindingModel, PhaseBindingModel phaseBindingModel) {

        Project project = mapper.projectModelToProject(bindingModel, context);

        if (!phaseBindingModel.getPhaseName().equals("")) {

            Phase phase = phaseService.createPhase(phaseBindingModel);
            phase.setProject(project);
            project.setPhases(Arrays.asList(phase));
        }

        projectRepository.saveAndFlush(project);
        return project;
    }

    /**
     * - This method gets all the projects from the repository.
     *
     * @return - returns list of projects.
     */
    @Override
    public List<ProjectBindingModel> getAllProjects() {
        List<ProjectBindingModel> projects = new ArrayList<>();
        projectRepository.findAll().forEach(project -> projects.add(mapper.projectToProjectBindingModel(project, context)));
        return projects;
    }

    /**
     * - This method gets all public the projects from the repository.
     *
     * @return - returns list of projects.
     */
    @Override
    public List<ProjectBindingModel> getAllPublicProjects() {
        List<ProjectBindingModel> projects = new ArrayList<>();
        projectRepository.findAll().forEach(project -> {
            if (project.getAccessStatus()){
                projects.add(mapper.projectToProjectBindingModel(project, context));
            }
        });

        return projects;
    }

    @Override
    public Project getProjectByTeamId(String id) {
        return projectRepository.findProjectByTeamId(Long.valueOf(id));
    }

    @Override
    public ProjectBindingModel getBindingProjectByTeamId(String id) {

        Project project = getProjectByTeamId(id);

        return mapper.projectToProjectBindingModel(project,context);
    }


    /**
     * - This method maps the project to the create binding model.
     *
     * @param id -  id of the project to be mapped.
     * @return - returns project binding model.
     */
    @Override
    public ProjectBindingModel findModelById(String id) {
        try {
            Project project = findById(Long.valueOf(id));
            return mapper.projectToProjectBindingModel(project, context);
        } catch (NumberFormatException exception) {
            throw new BadRequestException(id + ValidationConstants.INVALID_INPUT, exception);
        }
    }

    /**
     * This method maps the project to the create binding model.
     *
     * @param id -  id of the project to be mapped.
     * @return - returns project binding model.
     */
    @Override
    public ProjectBindingModel findModelById(Long id) {

        try {
            Project project = findById(id);
            return mapper.projectToProjectBindingModel(project, context);
        } catch (NumberFormatException exception) {
            throw new BadRequestException(id + ValidationConstants.INVALID_INPUT, exception);
        }

    }

    /**
     * - This method finds project by id.
     *
     * @param id - id of the project to find.
     * @return - returns the specific project if available.
     */
    public Project findById(Long id) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            throw new ProjectNotFoundException(ValidationConstants.PROJECT_NOT_FOUND);
        }
    }

    /**
     * - This method deletes project by id.
     *
     * @param id - id of the project to be deleted.
     */
    @Override
    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }

    /**
     * @param id - id of the project.
     * @return - returns list of phase binding models.
     */
    @Override
    public List<PhaseBindingModel> findAllPhaseModelsByProjectId(String id) {
        Project project = findById(Long.valueOf(id));
        return project.getPhases().stream()
                .map(phase -> phaseMapper.phaseToPhaseBindingModel(phase, context)).collect(Collectors.toList());
    }




    @Override
    public List<Project> getAllProjectsByTeamId(Long id) {
        return projectRepository.findAllProjectsByTeamId(Long.valueOf(id));
    }


}
