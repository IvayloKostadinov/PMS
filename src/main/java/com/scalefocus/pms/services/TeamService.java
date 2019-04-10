// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;


import com.scalefocus.pms.domain.Project;
import com.scalefocus.pms.domain.Team;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.models.binding.TeamBindingModel;

import java.util.List;
import java.util.Set;

public interface TeamService {

    List<Team> getAllTeams();

    Team getTeamByTeamName(String teamName);

    List<TeamBindingModel> getAllTeamsBindingModels();

    TeamBindingModel getTeamBindingModelById(Long id);

    Team getTeamById(Long id);

    void save(TeamBindingModel team);

    List<Project> getAllProjectsByTeamId(Long id);
    List<ProjectBindingModel> getAllProjectsBindingByTeamId(Long id);


}
