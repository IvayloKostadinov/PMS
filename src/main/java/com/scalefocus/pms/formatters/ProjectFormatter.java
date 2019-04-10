// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.formatters;

import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.services.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class ProjectFormatter implements Formatter<ProjectBindingModel> {

    private final ProjectService projectService;

    @Autowired
    public ProjectFormatter(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Override
    public ProjectBindingModel parse(String text, Locale locale) {
        Long id = Long.valueOf(text);
        return this.projectService.findModelById(id);
    }

    @Override
    public String print(ProjectBindingModel object, Locale locale) {
        if (object != null) {
            return object.getId().toString();
        }

        return "";
    }
}
