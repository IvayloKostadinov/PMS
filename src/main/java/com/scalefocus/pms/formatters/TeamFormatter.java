// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.formatters;

import com.scalefocus.pms.models.binding.TeamBindingModel;
import com.scalefocus.pms.services.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class TeamFormatter implements Formatter<TeamBindingModel> {

    private final TeamService teamService;

    @Autowired
    public TeamFormatter(TeamService teamService) {
        this.teamService = teamService;
    }

    @Override
    public TeamBindingModel parse(String text, Locale locale) throws ParseException {
        Long id = Long.valueOf(text);
        return this.teamService.getTeamBindingModelById(id);
    }

    @Override
    public String print(TeamBindingModel object, Locale locale) {
        if (object != null) {
            return object.getId().toString();
        }

        return "";
    }
}
