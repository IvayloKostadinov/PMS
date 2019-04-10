// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.about;

import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.controllers.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutController extends BaseController {

    public AboutController() {
    }

     /**
     * This method gets the about us page.
     * 
     * @return - returns the view for contact us page.
     */
    @GetMapping(MappingConstants.ABOUT_US)
    public ModelAndView aboutUs() {
        return this.view(ViewNameConstants.ABOUT_US);
    }
}
