package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Team;
import com.scalefocus.pms.domain.Role;
import com.scalefocus.pms.domain.User;
import com.scalefocus.pms.exceptions.UserDisabledException;
import com.scalefocus.pms.exceptions.UserNotFoundException;
import com.scalefocus.pms.mappers.CycleAvoidingMappingContext;
import com.scalefocus.pms.mappers.UserMapper;
import com.scalefocus.pms.models.binding.RegisterBindingModel;
import com.scalefocus.pms.models.binding.UserBindingModel;
import com.scalefocus.pms.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserServiceImpl service;
    private ProjectService projectService;
    private CycleAvoidingMappingContext context;

    @Mock
    BCryptPasswordEncoder encoder;


    UserMapper userMapper = UserMapper.INSTANCE;


    @Mock
    UserRepository userRepository;

    @Mock
    UserService userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new UserServiceImpl(userRepository, projectService);
        context = new CycleAvoidingMappingContext();
    }


    @Test
    public void testLoadByUserName() {
        //given
        User user = new User();
        user.setUsername(TestConstants.TEST_STRING);
        user.setStatus(true);
        Optional<User> optionalUser = Optional.of(user);

        //when
        when(userRepository.findByUsername(anyString())).thenReturn(optionalUser);

        //then
        UserDetails serviceUser = service.loadUserByUsername(TestConstants.TEST_STRING);
        assertEquals(user.getUsername(), serviceUser.getUsername());

    }

    @Test(expected = UserNotFoundException.class)
    public void testLoadByUserNameNotFound() {
        //given
        User user = new User();
        //when
        when(userRepository.findByUsername(anyString())).thenReturn(null);
        //then
        UserDetails serviceUser = service.loadUserByUsername(user.getUsername());
    }

    @Test(expected = UserDisabledException.class)
    public void testLoadByUserDisabled() {
        //given
        User user = new User();
        user.setStatus(false);
        user.setUsername(TestConstants.TEST_STRING);
        Optional<User> optionalUser = Optional.of(user);
        //when
        when(userRepository.findByUsername(anyString())).thenReturn(optionalUser);
        //then
        UserDetails serviceUser = service.loadUserByUsername(user.getUsername());
    }

    @Test
    public void testRegister() {
        RegisterBindingModel registerBindingModel = new RegisterBindingModel();
        registerBindingModel.setUsername(TestConstants.TEST_STRING);
        registerBindingModel.setPassword(TestConstants.TEST_STRING);
        registerBindingModel.setEmail(TestConstants.TEST_STRING);

        User user = new User(TestConstants.TEST_STRING,TestConstants.TEST_STRING,TestConstants.TEST_STRING);

        when(userRepository.save(any())).thenReturn(user);

        User registeredUser = service.register(registerBindingModel);

        assertEquals(registerBindingModel.getFirstName(), registeredUser.getFirstName());
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User();
        user.setUsername(TestConstants.TEST_STRING);
        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findByUsername(anyString()))
                .thenReturn(optionalUser);

        assertEquals(user.getUsername(), service.getUserByUsername(user.getUsername()).getUsername());

    }

    @Test(expected = UserNotFoundException.class)
    public void testGetUserByUsernameUserNotFound() {
        service.getUserByUsername(TestConstants.TEST_STRING);
    }

    @Test
    public void testShowUserById() {
        User user = new User();
        user.setId(1l);
        Optional<User> optionalUser = Optional.of(user);

        when(userRepository.findById(anyLong()))
                .thenReturn(optionalUser);

        assertEquals(user.getId(), service.getUserById(user.getId()).getId());

    }

    @Test(expected = UserNotFoundException.class)
    public void testShowUserByIdUserNotFound() {
        service.getUserById(TestConstants.TEST_ID);
    }

    @Test
    public void getUserBindingModeByRoleTest(){
        User user = new User(TestConstants.TEST_STRING,TestConstants.TEST_STRING,TestConstants.TEST_STRING);
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userRepository.getUserList(TestConstants.TEST_STRING_ADMIN)).thenReturn(users);

        List<UserBindingModel> bindingModel = service.getUserBindingModeByRole(TestConstants.TEST_STRING_ADMIN);

        assertEquals(bindingModel.get(0).getFirstName(), user.getFirstName());
    }

    @Test
    public void getUserBindingModelByIdTest(){
        User user = new User(TestConstants.TEST_STRING,TestConstants.TEST_STRING,TestConstants.TEST_STRING);

        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        UserBindingModel model =  service.getUserBindingModelById(anyLong());

        assertEquals(model.getFirstName(), user.getFirstName());
    }

    @Test(expected = UserNotFoundException.class)
    public void getUserBindingModelByIdNotFoundTest(){

        service.getUserBindingModelById(anyLong());

    }
    @Test
    public void getAllUsers() {

        User user = new User();
        List<User> users = new ArrayList<>();
        users.add(user);

        when(userService.getAllUsers()).thenReturn(users);

        List<User> userList = userService.getAllUsers();

        assertEquals(userList.size(), 1);

    }
}
