// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Partner;
import com.scalefocus.pms.models.binding.PartnerBindingModel;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PartnerMapper {

    PartnerMapper INSTANCE = Mappers.getMapper(PartnerMapper.class);

    PartnerBindingModel partnerToModel(Partner partner, @Context CycleAvoidingMappingContext context);

    Partner modelToPartner(PartnerBindingModel model, @Context CycleAvoidingMappingContext context);
}
