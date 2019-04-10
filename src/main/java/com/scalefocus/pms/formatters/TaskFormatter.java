// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.formatters;

import com.scalefocus.pms.models.binding.TaskBindingModel;
import com.scalefocus.pms.services.TaskService;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class TaskFormatter implements Formatter<TaskBindingModel> {
    private final TaskService taskService;

    @Autowired
    public TaskFormatter(TaskService taskService) {
        this.taskService = taskService;
    }


    @Override
    public TaskBindingModel parse(String text, Locale locale) throws ParseException {
        Long id = Long.valueOf(text);
        return this.taskService.getTaskBindingModelById(id);
    }

    @Override
    public String print(TaskBindingModel object, Locale locale) {
        if (object != null) {
            return object.getId().toString();
        }

        return StringUtils.EMPTY;
    }
}
