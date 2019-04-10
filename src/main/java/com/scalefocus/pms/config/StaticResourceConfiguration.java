// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.config;

import com.scalefocus.pms.constants.CloudTagConstants;

 import com.scalefocus.pms.formatters.RoleFormatter;
 import com.scalefocus.pms.formatters.UserFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    private final UserFormatter managerFormatter;
    private final RoleFormatter roleFormatter;

    @Autowired
    public StaticResourceConfiguration(UserFormatter managerFormatter, RoleFormatter roleFormatter) {
        this.managerFormatter = managerFormatter;
        this.roleFormatter = roleFormatter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(managerFormatter);
        registry.addFormatter(roleFormatter);
    }

}
