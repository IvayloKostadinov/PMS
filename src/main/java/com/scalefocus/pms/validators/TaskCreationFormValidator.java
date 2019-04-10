// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.validators;

import com.scalefocus.pms.models.binding.TaskBindingModel;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TaskCreationFormValidator implements Validator {


    @Override
    public boolean supports(Class<?> aclass) {
        return TaskBindingModel.class.equals(aclass);
    }


    /**
     * Validate Dates logic.
     *
     * @param object - TaskBinding model.
     * @param errors - if errors from
     */
    @Override
    public void validate(Object object, Errors errors) {
        final TaskBindingModel task = (TaskBindingModel) object;
        if (task.getDateCreated().before(task.getMeetingDate())) {
            errors.rejectValue("dateCreated", "invalid.dateCreated", "The date must be after meeting date.");
        }
        if (task.getDeadline().before(task.getDateCreated())) {
            errors.rejectValue("deadline", "invalid.deadline", "The date must be after assigned date.");
        }
    }
}
