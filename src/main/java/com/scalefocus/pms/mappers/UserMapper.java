// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Role;
import com.scalefocus.pms.domain.User;
import com.scalefocus.pms.models.binding.RegisterBindingModel;
import com.scalefocus.pms.models.binding.RoleBindingModel;
import com.scalefocus.pms.models.binding.UserBindingModel;

import org.mapstruct.Context;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "username", source = "model.username")
    @Mapping(target = "email", source = "model.email")
    @Mapping(target = "password", source = "model.password", qualifiedByName = "passwordEncode")
    @Mapping(target = "firstName", source = "model.firstName")
    @Mapping(target = "familyName", source = "model.familyName")
    @Mapping(target = "status", source = "model.status")
    @Mapping(target = "authorities", source = "model.authorities")
    User modelToUser(RegisterBindingModel model, @Context CycleAvoidingMappingContext context);

    User modelToManager(UserBindingModel model, @Context CycleAvoidingMappingContext context);

    UserBindingModel userToModel(User user, @Context CycleAvoidingMappingContext context);

    @Named("passwordEncode")
    default String passwordEncode(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }

}

