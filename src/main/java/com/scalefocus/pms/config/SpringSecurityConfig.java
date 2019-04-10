// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.config;

import com.scalefocus.pms.constants.MappingConstants;
import com.scalefocus.pms.constants.SecurityConfigConstants;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(SecurityConfigConstants.SLASH_PATH,
                MappingConstants.LOGIN, MappingConstants.REGISTER, MappingConstants.HOME,
                MappingConstants.NEWS_SHOW,MappingConstants.ABOUT_US,
                MappingConstants.GET_PARTNERS_MAP,MappingConstants.PROJECTS,MappingConstants.CONTACT_US)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage(MappingConstants.LOGIN)
                .permitAll()
                .loginProcessingUrl(MappingConstants.LOGIN)
                .usernameParameter(SecurityConfigConstants.USERNAME_PARAM)
                .passwordParameter(SecurityConfigConstants.PASSWORD_PARAM)
                .successForwardUrl(MappingConstants.LOGIN_POST)
                .failureUrl(MappingConstants.LOGIN_FAILED)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher(MappingConstants.LOGOUT))
                .logoutSuccessUrl(MappingConstants.LOGIN)
                .permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds(SecurityConfigConstants.VALIDITY_SECONDS
                        * SecurityConfigConstants.VALIDITY_SECONDS);
    }

    /**
     * Allow all css js and img files to be shown for all roles.
     *
     * @param web - Expects WebSecurity parameter
     * @throws Exception - throws Multiple Exceptions
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/*").antMatchers("/img/*").antMatchers("/js/*");
    }

}
