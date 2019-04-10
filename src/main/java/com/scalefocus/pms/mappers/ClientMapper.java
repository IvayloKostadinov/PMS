// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Client;
import com.scalefocus.pms.models.binding.ClientBindingModel;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper()
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    Client modelToClient(ClientBindingModel model,@Context CycleAvoidingMappingContext context);

    ClientBindingModel clientToModel(Client client,@Context CycleAvoidingMappingContext context);
}

