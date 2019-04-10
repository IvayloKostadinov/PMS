// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Task;
import com.scalefocus.pms.models.binding.TaskBindingModel;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);


    TaskBindingModel taskToModel(Task task, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);


    Task modelToTask(TaskBindingModel model, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);
}
