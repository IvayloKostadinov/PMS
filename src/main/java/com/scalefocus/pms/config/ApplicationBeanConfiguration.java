// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.config;

import com.scalefocus.pms.mappers.MeetingMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public BCryptPasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringSecurityDialect springSecurityDialect() {
        return new SpringSecurityDialect();
    }

//    @Bean
//    public MeetingMapper meetingMapper() throws IllegalAccessException, InstantiationException {
//        return MeetingMapper.INSTANCE;
//    }
}
