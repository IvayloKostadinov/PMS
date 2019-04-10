// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;


import com.scalefocus.pms.domain.Meeting;
import com.scalefocus.pms.mappers.CycleAvoidingMappingContext;
import com.scalefocus.pms.mappers.MeetingMapper;
import com.scalefocus.pms.models.binding.MeetingBindingModel;
import com.scalefocus.pms.repositories.MeetingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MeetingServiceImpl implements MeetingService {

    private final MeetingMapper mapper;
    private final MeetingRepository meetingRepository;
    private final CycleAvoidingMappingContext context;

    /**
     * This is the constructor autowiring all of the dependencies.
     *
     * @param meetingRepository - meeting repository.
     */
    @Autowired
    public MeetingServiceImpl(MeetingRepository meetingRepository) {
        this.mapper = MeetingMapper.INSTANCE;
        this.meetingRepository = meetingRepository;
        this.context = new CycleAvoidingMappingContext();
    }


    /**
     * This method registers a meeting.
     *
     * @param bindingModel - This is already filled binding model
     * @return - meeting if successfully registered
     */
    @Override
    public Meeting registerMeeting(MeetingBindingModel bindingModel) {

        Meeting meeting = mapper.meetingModelToMeeting(bindingModel, context);
        meetingRepository.saveAndFlush(meeting);

        return meeting;
    }

    /**
     * - This method maps the meeting to the  binding model.
     *
     * @param id -  id of the meeting to be mapped.
     * @return - returns meeting binding model.
     */
    @Override
    public MeetingBindingModel getMeetingById(Long id) {
        Optional<Meeting> meeting = meetingRepository.findById(id);

        if (meeting.isPresent()) {
            return mapper.meetingToMeetingBindingModel(meeting.get(), context);
        }
        return null;
    }
}