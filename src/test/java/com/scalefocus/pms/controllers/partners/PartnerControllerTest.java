// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.partners;

import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.services.PartnersService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class PartnerControllerTest {

    private PartnerController controller;

    @Mock
    PartnersService partnersService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        controller = new PartnerController(partnersService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void showPartners() throws Exception {
        when(partnersService.generateCloudTag()).thenReturn(true);
        mockMvc.perform(get(MappingConstants.GET_PARTNERS_MAP))
                .andExpect(status().isOk())
                .andExpect(view().name(ViewNameConstants.VIEW_PARTNERS_HTML));

    }
}

