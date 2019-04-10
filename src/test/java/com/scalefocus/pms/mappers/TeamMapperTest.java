package com.scalefocus.pms.mappers;

import static org.junit.Assert.assertEquals;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Team;
import com.scalefocus.pms.models.binding.TeamBindingModel;

import org.junit.Before;
import org.junit.Test;

/**
 * TeamMapperTest
 */
public class TeamMapperTest {

    private TeamMapper mapper;
    private TeamBindingModel bindingModel;
    private CycleAvoidingMappingContext context;

    @Before
    public void setUp() {
        bindingModel = new TeamBindingModel();
        mapper = TeamMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();

    }

    @Test
    public void testModelToTeamMapper() {
        Team team;
        bindingModel.setTeamName(TestConstants.TEST_STRING);
        team = mapper.modelToTeam(bindingModel, context);
        
        assertEquals(team.getTeamName(), bindingModel.getTeamName());
    }


    @Test
    public void testTeamToModelMapper() {
        Team team = new Team(TestConstants.TEST_STRING);
        bindingModel = mapper.teamToModel(team, context);
        
        assertEquals(team.getTeamName(), bindingModel.getTeamName());
    }
}