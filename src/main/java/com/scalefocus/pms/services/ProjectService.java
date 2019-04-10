// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.domain.Project;
import com.scalefocus.pms.models.binding.PhaseBindingModel;
import com.scalefocus.pms.models.binding.ProjectBindingModel;

import java.util.List;

public interface ProjectService {
    Project registerProject(ProjectBindingModel bindingModel, PhaseBindingModel phaseBindingModel);

    ProjectBindingModel findModelById(String id);

    ProjectBindingModel findModelById(Long id);

    List<ProjectBindingModel> getAllProjects();

    List<ProjectBindingModel> getAllPublicProjects();

    Project getProjectByTeamId(String id);

    ProjectBindingModel getBindingProjectByTeamId(String id);

    void deleteById(Long id);

    List<PhaseBindingModel> findAllPhaseModelsByProjectId(String id);

    Project findById(Long id);


    List<Project> getAllProjectsByTeamId(Long id);

}
