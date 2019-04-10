// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Project;
import com.scalefocus.pms.models.binding.ProjectBindingModel;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = MeetingMapper.class)
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectBindingModel projectToProjectBindingModel(Project project, @Context CycleAvoidingMappingContext context);

    Project projectModelToProject(ProjectBindingModel model, @Context CycleAvoidingMappingContext context);
}

