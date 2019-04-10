// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.home;

import com.scalefocus.pms.constants.AttributeConstants;
import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.SecurityConfigConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.controllers.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class HomeController extends BaseController {

    public HomeController() {
    }

    /**
     * This method gets the hole page for the logged user
     *
     * @param modelAndView - Model and View for the page.
     * @return - Returns index page.
     */
    @GetMapping(MappingConstants.HOME)
    public ModelAndView home(ModelAndView modelAndView) {

        return this.view(ViewNameConstants.INDEX, modelAndView);
    }

    @GetMapping(SecurityConfigConstants.SLASH_PATH)
    public ModelAndView index() {
        return this.view(ViewNameConstants.INDEX);
    }
}
