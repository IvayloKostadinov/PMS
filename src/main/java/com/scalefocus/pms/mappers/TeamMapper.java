// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Team;
import com.scalefocus.pms.models.binding.TeamBindingModel;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * TeamMapper.
 */
@Mapper
public interface TeamMapper {

    TeamMapper INSTANCE = Mappers.getMapper(TeamMapper.class);

    Team modelToTeam(TeamBindingModel model, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

    TeamBindingModel teamToModel(Team model, @Context CycleAvoidingMappingContext cycleAvoidingMappingContext);

}