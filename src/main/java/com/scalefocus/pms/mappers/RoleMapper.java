// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Role;
import com.scalefocus.pms.models.binding.RoleBindingModel;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    Role roleBindingModel(RoleBindingModel model, @Context CycleAvoidingMappingContext context);

    RoleBindingModel roleToRoleBindingModel(Role role, @Context CycleAvoidingMappingContext context);
}
