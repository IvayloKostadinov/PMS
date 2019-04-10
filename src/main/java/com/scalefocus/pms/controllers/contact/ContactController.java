// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers.contact;

import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.controllers.BaseController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ContactController extends BaseController {

    public ContactController() {
    }

    /**
     * This method gets the contact us page.
     * 
     * @return - returns the view for contact us page.
     */
    @GetMapping(MappingConstants.CONTACT_US)
    public ModelAndView contactUs() {
        return this.view(ViewNameConstants.CONTACT_US);
    }
}
