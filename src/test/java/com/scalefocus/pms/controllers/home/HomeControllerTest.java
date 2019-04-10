package com.scalefocus.pms.controllers.home;

import com.scalefocus.pms.constants.ViewNameConstants;
import org.junit.Before;
import org.junit.Test;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class HomeControllerTest {

    private HomeController controller;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {

        controller = new HomeController();
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/templates/");
        viewResolver.setSuffix(".html");
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(viewResolver).build();
    }

    @Test
    public void index() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNameConstants.INDEX));
    }
    @Test
    public void home() throws Exception{
        String viewName = controller.home(new ModelAndView()).getViewName();
        assertEquals(ViewNameConstants.INDEX, viewName);

    }

}