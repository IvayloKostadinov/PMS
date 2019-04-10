// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.formatters;

import com.scalefocus.pms.models.binding.MeetingBindingModel;
import com.scalefocus.pms.services.MeetingService;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;

@Service
public class MeetingFormatter implements Formatter<MeetingBindingModel> {

    private final MeetingService meetingService;

    @Autowired
    public MeetingFormatter(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @Override
    public MeetingBindingModel parse(String text, Locale locale) throws ParseException {
        Long id = Long.valueOf(text);
        return this.meetingService.getMeetingById(id);
    }

    @Override
    public String print(MeetingBindingModel object, Locale locale) {
        if (object != null) {
            return object.getId().toString();
        }

        return StringUtils.EMPTY;
    }
}
