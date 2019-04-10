// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.login;

import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.controllers.login.LoginController;
import com.scalefocus.pms.domain.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.scalefocus.pms.services.UserService;


public class LoginControllerTest {

    @Mock
    private UserService userService;
    private MockMvc mockMvc;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        LoginController controller = new LoginController(userService);
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }


    @Test
    public void getLoginPage() throws Exception {
        mockMvc.perform(get(MappingConstants.LOGIN))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNameConstants.LOGIN));
    }

    @Test
    public void loginPost() throws Exception {
        mockMvc.perform(post(MappingConstants.LOGIN_POST))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name(ViewNameConstants.REDIRECT + ViewNameConstants.HOME));
    }

    @Test
    public void getRegisterPage() throws Exception {
        mockMvc.perform(get(MappingConstants.REGISTER))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNameConstants.REGISTER));
    }

    @Test
    public void postRegister() {
        User user = new User();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, TestConstants.TEST_STRING_ADMIN);
        SecurityContextHolder.getContext().setAuthentication(token);
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Assert.assertNotNull(principal);
    }

    @Test
    public void loginFailed() throws Exception {
        mockMvc.perform(get(MappingConstants.LOGIN_FAILED))
                .andExpect(status().isOk())
                .andExpect(view().name(MappingConstants.LOGIN_FAILED));
    }

}