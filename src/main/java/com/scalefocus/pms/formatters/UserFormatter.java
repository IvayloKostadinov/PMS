// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.formatters;

import com.scalefocus.pms.models.binding.RoleBindingModel;
import com.scalefocus.pms.models.binding.UserBindingModel;
import com.scalefocus.pms.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Service
public class UserFormatter implements Formatter<UserBindingModel> {

    private final UserService userService;

    @Autowired
    public UserFormatter(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserBindingModel parse(String text, Locale locale) throws ParseException {
        Long id = Long.valueOf(text);
        return this.userService.getUserBindingModelById(id);
    }

    @Override
    public String print(UserBindingModel object, Locale locale) {
        if (object != null) {
            return object.getId().toString();
        }

        return "";
    }
}
