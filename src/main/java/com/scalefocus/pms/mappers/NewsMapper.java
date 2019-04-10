// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.News;
import com.scalefocus.pms.models.binding.NewsBindingModel;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NewsMapper {

    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    NewsBindingModel newsToModel(News news, @Context CycleAvoidingMappingContext context);

    News modelToNews(NewsBindingModel model, @Context CycleAvoidingMappingContext context);
}
