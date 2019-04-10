// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.partners;

import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.services.PartnersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Controller for partner functionality.
 */
@Controller
public class PartnerController {

    @Autowired
    private final PartnersService partnersService;

    /**
     * Call service method witch generates image on page "Partners".
     *
     * @return partners html page.
     * @throws IOException when the picture is not found.
     */
    @GetMapping(MappingConstants.GET_PARTNERS_MAP)
    public ModelAndView showPartners() throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ViewNameConstants.VIEW_PARTNERS_HTML);
        partnersService.generateCloudTag();

        return modelAndView;
    }

    public PartnerController(final PartnersService partnersService) {
        this.partnersService = partnersService;
    }
}
