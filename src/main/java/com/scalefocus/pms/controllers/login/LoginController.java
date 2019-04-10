// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.login;

import com.scalefocus.pms.constants.AttributeConstants;
import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.controllers.BaseController;
import com.scalefocus.pms.domain.User;
import com.scalefocus.pms.models.binding.RegisterBindingModel;
import com.scalefocus.pms.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class LoginController extends BaseController {
    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GetLoginPage method.
     *
     * @param error - error in the view.
     * @param model - model object for the page.
     * @return ModelAndView - returns the login view.
     */
    @GetMapping(MappingConstants.LOGIN)
    public ModelAndView getLoginPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute(AttributeConstants.ERROR, error);
        }

        return this.view(ViewNameConstants.LOGIN);
    }

    /**
     * loginPost method that redirects to user home.
     *
     * @return returns redirected view for user.
     */
    @PostMapping(value = MappingConstants.LOGIN_POST)
    public ModelAndView loginPost() {
        return this.redirect(ViewNameConstants.HOME);
    }

    /**
     * getRegisterPage method.
     *
     * @param error - error in the page.
     * @param model - Page model that hold attributes.
     * @return - register view.
     */
    @GetMapping(MappingConstants.REGISTER)
    public ModelAndView getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute(AttributeConstants.ERROR, error);
        }

        return this.view(ViewNameConstants.REGISTER);
    }

    /**
     * postRegister Method.
     *
     * @param userRegisterBindingModel - Binding Model for User entity.
     * @return - register view.
     */
    @PostMapping(value = MappingConstants.REGISTER)
    public ModelAndView postRegister(@Valid @ModelAttribute RegisterBindingModel userRegisterBindingModel) {
        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPassword())) {
            return this.view(ViewNameConstants.REGISTER);
        }

        userService.register(userRegisterBindingModel);

        return this.redirect(ViewNameConstants.ALL_USERS_PAGE_VIEW);
    }

    /**
     * MyProfile method. Creates Get Request for currently logged in user.
     *
     * @param model       - Model model.
     * @param currentUser - UserDetails currentUser.
     * @return - returns view with the name of myprofile.
     */
    @GetMapping(MappingConstants.PROFILE)
    public ModelAndView myProfile(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        User user = userService.getUserByUsername(currentUser.getUsername());
        model.addAttribute(AttributeConstants.CURRENT_USER, user);

        return this.view(ViewNameConstants.MY_PROFILE_VIEW);
    }


    /**
     * @return - return login fail error page.
     */
    @GetMapping(MappingConstants.LOGIN_FAILED)
    public ModelAndView loginFailed() {
        return this.view(MappingConstants.LOGIN_FAILED);
    }
}
