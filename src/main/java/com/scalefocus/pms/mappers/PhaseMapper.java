// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Phase;
import com.scalefocus.pms.models.binding.PhaseBindingModel;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhaseMapper {

    PhaseMapper INSTANCE = Mappers.getMapper(PhaseMapper.class);

    Phase modelToPhase(PhaseBindingModel model, @Context CycleAvoidingMappingContext context);

    PhaseBindingModel phaseToPhaseBindingModel(Phase phase, @Context CycleAvoidingMappingContext context);
}
