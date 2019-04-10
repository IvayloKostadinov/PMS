// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.models.binding.TaskBindingModel;

public interface TaskService {
    boolean addTask(TaskBindingModel bindingModel);

    TaskBindingModel getTaskBindingModelById(Long id);
}
