// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.mappers;

import com.scalefocus.pms.domain.Meeting;
import com.scalefocus.pms.models.binding.MeetingBindingModel;

import org.mapstruct.Context;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MeetingMapper {

    MeetingMapper INSTANCE = Mappers.getMapper(MeetingMapper.class);

    @Mapping(target = "agenda", source = "model.meetingTheme")
    @Mapping(target = "date", source = "model.startDate")
    @Mapping(target = "endTime", source = "model.endTime")
    @Mapping(target = "user", source = "model.createdBy")
    @Mapping(target = "project", source = "model.project")
    @Mapping(target = "meetingType", source = "model.meetingType")
    @Mapping(target = "notes", source = "model.notes")
    Meeting meetingModelToMeeting(MeetingBindingModel model, @Context CycleAvoidingMappingContext context);

    @Mapping(target = "meetingTheme", source = "meeting.agenda")
    @Mapping(target = "startDate", source = "meeting.date")
    @Mapping(target = "createdBy", source = "meeting.user")
    @Mapping(target = "project", source = "meeting.project")
    MeetingBindingModel meetingToMeetingBindingModel(Meeting meeting, @Context CycleAvoidingMappingContext context);
}
