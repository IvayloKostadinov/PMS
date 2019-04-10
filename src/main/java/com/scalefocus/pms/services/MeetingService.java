// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.domain.Meeting;
import com.scalefocus.pms.models.binding.MeetingBindingModel;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.models.binding.UserBindingModel;

public interface MeetingService {

    Meeting registerMeeting(MeetingBindingModel bindingModel);

    MeetingBindingModel getMeetingById(final Long id);

}