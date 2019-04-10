package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Role;
import com.scalefocus.pms.domain.User;
import com.scalefocus.pms.models.binding.RegisterBindingModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotEquals;

public class UserMappersTest {

    private UserMapper userMapper = UserMapper.INSTANCE;
    private RegisterBindingModel registerBindingModel;
    private CycleAvoidingMappingContext context;
    BCryptPasswordEncoder encoder;

    @Before
    public void setUp(){
        context = new CycleAvoidingMappingContext();
    }

    @Test
    public void testModelToUserMapper(){
        User user = new User();
        encoder = new BCryptPasswordEncoder();
        String password = TestConstants.TEST_STRING;
        String encodedPassword = encoder.encode(password);
        Set<Role> roles = new HashSet<Role>();
        roles.add(new Role(TestConstants.TEST_STRING_ADMIN));

        registerBindingModel = new RegisterBindingModel();
        registerBindingModel.setUsername(TestConstants.TEST_STRING);
        registerBindingModel.setEmail(TestConstants.TEST_STRING_ADMIN_EMAIL);
        registerBindingModel.setPassword(password);
        registerBindingModel.setFirstName(TestConstants.TEST_STRING);
        registerBindingModel.setFamilyName(TestConstants.TEST_STRING);
        registerBindingModel.setStatus(true);
        registerBindingModel.setAuthorities(roles);

        user = userMapper.modelToUser(registerBindingModel,context);
        assertNotEquals(user.getPassword(), registerBindingModel.getPassword());
    }
}
