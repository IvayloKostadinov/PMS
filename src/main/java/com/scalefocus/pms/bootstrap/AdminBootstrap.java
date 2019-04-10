// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.bootstrap;


import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Role;
import com.scalefocus.pms.domain.User;
import com.scalefocus.pms.repositories.RoleRepository;
import com.scalefocus.pms.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

@Component
public class AdminBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    BCryptPasswordEncoder encoder;

    /**
     * AdminBootstrap Public Constructor.
     *
     * @param userRepository - UserRepository userRepository.
     * @param roleRepository - RoleRepository roleRepository.
     * @param encoder        - BCryptPasswordEncoder encoder.
     */
    @Autowired
    public AdminBootstrap(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
    }


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        createAdmin();
    }

    private User createAdmin() {
        User admin = new User();
        admin.setFirstName(TestConstants.TEST_STRING_ADMIN);
        admin.setFamilyName(TestConstants.TEST_STRING_ADMIN);
        admin.setUsername(TestConstants.TEST_STRING_ADMIN);
        admin.setPassword(encoder.encode(TestConstants.TEST_STRING_ADMIN));
        admin.setEmail(TestConstants.TEST_STRING_ADMIN_EMAIL);
        admin.setStatus(true);
        Role role = roleRepository.findById(1L).get();
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        admin.setAuthorities(roles);

        List<User> dbUsers = userRepository.findAll();
        if (dbUsers.isEmpty()) {
            userRepository.save(admin);
        }

        return admin;
    }
}
