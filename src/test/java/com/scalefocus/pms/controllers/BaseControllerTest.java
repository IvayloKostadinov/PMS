package com.scalefocus.pms.controllers;

import com.scalefocus.pms.constants.ViewNameConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.*;

public class BaseControllerTest {

    private MockMvc mockMvc;
    private BaseController controller;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new BaseController();
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }


    @Test
    public void viewWithModel() {
        String view = "viewName";
        String viewName = controller.view(view, new ModelAndView()).getViewName();
        Assert.assertEquals(viewName , view);
    }

    @Test
    public void redirect() {
        String view = "viewName";
        String viewName = controller.redirect(view).getViewName();
        Assert.assertEquals(viewName, ViewNameConstants.REDIRECT +view);
    }
}