// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.team;

import com.scalefocus.pms.controllers.BaseController;
import com.scalefocus.pms.models.binding.TeamBindingModel;
import com.scalefocus.pms.services.TeamService;
import com.scalefocus.pms.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class TeamController extends BaseController {

    private final TeamService teamService;
    private final UserService userService;


    public TeamController(TeamService teamService, UserService userService) {
        this.teamService = teamService;
        this.userService = userService;
    }

    @GetMapping("teams/all")
    public ModelAndView getAllTeamsPage(ModelAndView model){

        model.addObject("teams", teamService.getAllTeams());

        return this.view("teams/all-teams.html",model);
    }

    @GetMapping("teams/{id}/members")
    public ModelAndView getMembersPage(@PathVariable String id, ModelAndView model){

        model.addObject("team", teamService.getTeamBindingModelById(Long.valueOf(id)));

        return this.view("teams/team-members.html",model);
    }

    @GetMapping("teams/create")
    public ModelAndView getMembersPage(ModelAndView model){

        model.addObject("allUsers", userService.getAllUserBindingModels());
        model.addObject("team", new TeamBindingModel());

        return this.view("teams/create-team",model);
    }

    @PostMapping("teams/new")
    public ModelAndView createTeam(@ModelAttribute TeamBindingModel team, ModelAndView model) {
        teamService.save(team);

        return this.redirect("teams/all");
    }
}
