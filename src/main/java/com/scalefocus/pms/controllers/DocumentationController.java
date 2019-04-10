// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.controllers;

import com.scalefocus.pms.constants.AttributeConstants;
import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.models.binding.DocumentationBindingModel;
import com.scalefocus.pms.services.DocumentationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class DocumentationController extends BaseController {
    private final DocumentationService documentationService;

    /**
     * This is the constructor autowiring all the dependencies needed.
     *
     * @param documentationService - The service class for the  Documentation entity.
     */
    @Autowired
    public DocumentationController(DocumentationService documentationService) {
        this.documentationService = documentationService;
    }

    /**
     *
     * @param id - Path variable for project id.
     * @param model - model and view for the page.
     * @return - returns the view for documentation.
     */
    @GetMapping(MappingConstants.DOCUMENTATION)
    public ModelAndView getDocumentationPage(@PathVariable String id, ModelAndView model) {

        model.addObject(AttributeConstants.DOCUMENTATION_MODEL,
                documentationService.findDocumentationByProjectId(id).get(0));

        model.setViewName(MappingConstants.DOCUMENTATION_FORM);
        return model;
    }

    /**
     *
     * @param documentationBindingModel - The Binding Model for the Documentation.
     * @return - returns the home view.
     */
    @PostMapping(MappingConstants.SAVE_DOCUMENTATION)
    public ModelAndView saveOrUpdate(@Valid @ModelAttribute DocumentationBindingModel documentationBindingModel) {

        documentationService.saveDocumentation(documentationBindingModel);

        return redirect(ViewNameConstants.PROJECTS);
    }

}
