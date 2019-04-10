package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.constants.ValidationConstants;
import com.scalefocus.pms.domain.Team;
import com.scalefocus.pms.models.binding.TeamBindingModel;
import com.scalefocus.pms.repositories.TeamRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TeamServiceImplTest {
    private TeamService teamService;

    @Mock
    private TeamRepository teamRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        teamService = new TeamServiceImpl(teamRepository);
    }

    @Test
    public void getAllTeams() {

        Team team = new Team();
        List<Team> teams = new ArrayList<>();
        teams.add(team);

        when(teamService.getAllTeams()).thenReturn(teams);

        List<Team> teamList = teamService.getAllTeams();

        assertEquals(teamList.size(), 1);
        verify(teamRepository, times(1)).findAll();
        verify(teamRepository, never()).findById(anyLong());

    }

    @Test
    public void getTeamByIdTest(){
        Team team = new Team(TestConstants.TEST_STRING);
        when(teamRepository.findById(anyLong())).thenReturn(Optional.of(team));

        assertEquals(teamService.getTeamById(anyLong()),team);
    }

    @Test
    public void getTeamByTeamNameTest(){
        Team team = new Team(TestConstants.TEST_STRING);

        when(teamRepository.findTeamByTeamName(TestConstants.TEST_STRING)).thenReturn(team);

        assertEquals(teamService.getTeamByTeamName(TestConstants.TEST_STRING),team);
    }

    @Test
    public void getTeamBindingModelByIdTest(){
        Team team = new Team(TestConstants.TEST_STRING);

        when(teamRepository.findById(anyLong())).thenReturn(Optional.of(team));

        TeamBindingModel model = teamService.getTeamBindingModelById(TestConstants.TEST_ID);

        assertEquals(model.getTeamName(), team.getTeamName());
    }

    @Test
    public void getAllTeamsBindingModelsTest(){
        Team team = new Team(TestConstants.TEST_STRING);
        List<Team> teams = new ArrayList<>();
        teams.add(team);

        when(teamService.getAllTeams()).thenReturn(teams);

        List<TeamBindingModel> bindingModels = teamService.getAllTeamsBindingModels();

        assertEquals(bindingModels.get(0).getTeamName(), team.getTeamName());


    }
}