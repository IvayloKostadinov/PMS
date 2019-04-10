// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Documentation;
import com.scalefocus.pms.models.binding.DocumentationBindingModel;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(uses = UserMapper.class)
public interface DocumentationMapper {

    DocumentationMapper INSTANCE = Mappers.getMapper(DocumentationMapper.class);

    DocumentationBindingModel documentationToModel(Documentation documentation,
                                                   @Context CycleAvoidingMappingContext context);

    Documentation modelToDocumentation(DocumentationBindingModel model,
                                       @Context CycleAvoidingMappingContext context);
}
