// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.ValidationConstants;
import com.scalefocus.pms.domain.Project;
import com.scalefocus.pms.domain.Team;
import com.scalefocus.pms.exceptions.TeamNotFoundException;
import com.scalefocus.pms.mappers.CycleAvoidingMappingContext;
import com.scalefocus.pms.mappers.ProjectMapper;
import com.scalefocus.pms.mappers.TeamMapper;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.models.binding.TeamBindingModel;
import com.scalefocus.pms.repositories.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamRepository teamRepository;
    private final TeamMapper mapper;
    private final CycleAvoidingMappingContext context;

    /**
     * Autowired constructor taking one paramter.
     *
     * @param teamRepository - TeamRepository teamRepository.
     */
    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
        this.mapper = TeamMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();
    }

    /**
     * Returns a list of all teams in the system.
     *
     * @return List of teams.
     */
    @Override
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    /**
     * Get team by team name.
     * @param teamName - Team name.
     * @return - returns a Team object.
     */
    @Override
    public Team getTeamByTeamName(String teamName) {
        return teamRepository.findTeamByTeamName(teamName);
    }

    /**
     * Get all Teams binding model.
     * @return - List of Team binding model.
     */
    @Override
    public List<TeamBindingModel> getAllTeamsBindingModels() {
        List<TeamBindingModel> teamBindingModels = new ArrayList<>();

        List<Team> teams = this.getAllTeams();

        for (Team team : teams) {
            TeamBindingModel model = mapper.teamToModel(team, context);
            teamBindingModels.add(model);
        }

        return teamBindingModels;
    }

    /**
     * Gets team binding model by id.
     *
     * @param id - id of the team binding which is needed.
     * @return - searched team binding model.
     */
    @Override
    public TeamBindingModel getTeamBindingModelById(Long id) {
        TeamBindingModel model;

        Team team = this.getTeamById(id);
        model = mapper.teamToModel(team, context);

        return model;
    }

    /**
     * Gets team by id.
     * @param id - id of the searched team.
     * @return - found team from the database.
     */
    @Override
    public Team getTeamById(Long id) {

        Team team = this.teamRepository.findById(id).orElse(null);

        if (team == null) {
            throw new TeamNotFoundException(ValidationConstants.TEAM_NOT_FOUND);
        }

        return team;
    }

    @Override
    public void save(TeamBindingModel team) {
        teamRepository.save(mapper.modelToTeam(team,context));
    }

    @Override
    public List<Project> getAllProjectsByTeamId(Long id) {
        return teamRepository.findById(id).get().getProjects();
    }

    @Override
    public List<ProjectBindingModel> getAllProjectsBindingByTeamId(Long id) {
        List<ProjectBindingModel> projectBindingModelList = new ArrayList<>();
        getAllProjectsByTeamId(id).forEach(project -> projectBindingModelList
                .add(ProjectMapper.INSTANCE
                        .projectToProjectBindingModel(project,context)));

        return projectBindingModelList;
    }


}
