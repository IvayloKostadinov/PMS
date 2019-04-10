// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.user;

import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.controllers.BaseController;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.models.binding.TeamBindingModel;
import com.scalefocus.pms.models.binding.UserBindingModel;
import com.scalefocus.pms.services.RoleService;
import com.scalefocus.pms.services.UserService;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Controller
public class UserController extends BaseController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    /**
     * getAllUsersPage Method.
     *
     * @param model - Model model.
     * @return - Returns page with all of the registered users.
     */
    @GetMapping(MappingConstants.ALL_USERS_PAGE)
    public ModelAndView getAllUsersPage(Model model) {

        model.addAttribute("users", userService.getAllUserBindingModels());

        return this.view(ViewNameConstants.ALL_USERS_PAGE_VIEW);
    }

    /**
     * Delete user Method.
     *
     * @param id    - Path variable , it represents the user id.
     * @param model - Model model.
     * @return - redirects to page with all of the registered users.
     */
    @GetMapping(MappingConstants.DELETE_USER)
    public ModelAndView deleteUser(@PathVariable String id, Model model) {
        userService.deleteUserById(Long.valueOf(id));
        return this.redirect(ViewNameConstants.ALL_USERS_PAGE_VIEW);
    }

    /**
     * getProfilePage Method.
     *
     * @param id    - Path variable , it represents the user id.
     * @param model - Model model.
     * @return returns user's profile page.
     */
    @GetMapping(MappingConstants.PROFILE_USER)
    public ModelAndView getProfilePage(@PathVariable String id, ModelAndView model) {
        List<ProjectBindingModel> bindingPojectsList = new ArrayList<>();
       userService.getUserBindingModelById(Long.valueOf(id)).getTeams()
                .forEach(team -> {
                    bindingPojectsList.addAll(team.getProjects());
                });
//                .stream().map(TeamBindingModel::getProjects).collect(Collectors.toList())

        model.addObject("user", userService.getUserBindingModelById(Long.valueOf(id)));
        model.addObject("projects", bindingPojectsList);
//        model.addObject("projects", userService.getAllUserProjects(Long.valueOf(id)));

        return this.view(ViewNameConstants.PROFILE_USER, model);
    }

    /**
     * getUpdateUserForm method.
     *
     * @param id    - Path variable , it represents the user id.
     * @param model - Model model.
     * @return - returns edit user form.
     */
    @GetMapping(MappingConstants.EDIT_USER)
    public ModelAndView getUpdateUserForm(@PathVariable String id, ModelAndView model) {
        List<ProjectBindingModel> bindingPojectsList = new ArrayList<>();
        userService.getUserBindingModelById(Long.valueOf(id)).getTeams()
                .forEach(team -> {
                    bindingPojectsList.addAll(team.getProjects());
                });

        model.addObject("user", userService.getUserBindingModelById(Long.valueOf(id)));
        model.addObject("projects", bindingPojectsList);
        model.addObject("roles", roleService.getAllRolesBinding());
//        model.addObject("roleOne", new RoleBindingModel());

        return this.view(ViewNameConstants.EDIT_PROFILE_USER, model);
    }

    /**
     * Post method for updating the user.
     *
     * @param userBindingModel - UserBindingMode userBindingModel.
     * @return - redirects to user's profile.
     */
    @PostMapping(MappingConstants.EDIT_PROFILE_UDER)
    public ModelAndView updateUser(@ModelAttribute UserBindingModel userBindingModel) {
        userService.update(userBindingModel);

        return this.redirect("users/" + userBindingModel.getId() + "/profile");
    }
}
