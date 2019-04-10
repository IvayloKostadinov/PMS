package com.scalefocus.pms.formatters;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.models.binding.TeamBindingModel;
import com.scalefocus.pms.models.binding.UserBindingModel;
import com.scalefocus.pms.services.TeamService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.text.ParseException;
import java.util.Locale;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class TeamFormatterTest {

    @Mock
    private TeamService teamService;

    private TeamFormatter formatter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        this.formatter = new TeamFormatter(teamService);
    }

    @Test
    public void parseTest() throws ParseException {
        TeamBindingModel bindingModel = new TeamBindingModel();
        bindingModel.setTeamName(TestConstants.TEST_STRING);

        when(teamService.getTeamBindingModelById(TestConstants.TEST_ID)).thenReturn(bindingModel);

        assertEquals(formatter.parse(TestConstants.TEST_ID.toString(),new Locale("en")),bindingModel);
    }

    @Test
    public void printTest() {

        TeamBindingModel bindingModel = new TeamBindingModel();
        bindingModel.setId(TestConstants.TEST_ID);

        String id = formatter.print(bindingModel,new Locale("en"));

        assertEquals(id, bindingModel.getId().toString());
    }
}