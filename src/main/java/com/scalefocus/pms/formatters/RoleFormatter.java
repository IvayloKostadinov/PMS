// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.formatters;

import com.scalefocus.pms.models.binding.RoleBindingModel;
import com.scalefocus.pms.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Locale;


@Service
public class RoleFormatter implements Formatter<RoleBindingModel> {

    private final RoleService roleService;

    @Autowired
    public RoleFormatter(RoleService roleService) {
        this.roleService = roleService;
    }


    @Override
    public RoleBindingModel parse(String id, Locale locale) throws ParseException {

        return roleService.getRoleBindingById(Long.valueOf(id));
    }

    @Override
    public String print(RoleBindingModel roleBindingModels, Locale locale) {
        if (roleBindingModels != null) {
            return roleBindingModels.getId().toString();
        }

        return "";
    }
}
