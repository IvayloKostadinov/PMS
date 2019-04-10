// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Message;
import com.scalefocus.pms.models.binding.MessageBindingModel;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {

    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageBindingModel messageToModel(Message message, @Context CycleAvoidingMappingContext context);

    Message modelToMessage(MessageBindingModel model, @Context CycleAvoidingMappingContext context);
}
