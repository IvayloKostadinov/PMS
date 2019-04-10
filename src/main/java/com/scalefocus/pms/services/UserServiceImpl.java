// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.ExceptionMessageConstants;
import com.scalefocus.pms.constants.ValidationConstants;
import com.scalefocus.pms.domain.User;
import com.scalefocus.pms.exceptions.UserDisabledException;
import com.scalefocus.pms.exceptions.UserNotFoundException;
import com.scalefocus.pms.mappers.CycleAvoidingMappingContext;
import com.scalefocus.pms.mappers.UserMapper;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.models.binding.RegisterBindingModel;
import com.scalefocus.pms.models.binding.TeamBindingModel;
import com.scalefocus.pms.models.binding.UserBindingModel;
import com.scalefocus.pms.repositories.UserRepository;

import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository userRepository;
    private final CycleAvoidingMappingContext context;
    private final ProjectService projectService;

    /**
     * This is the constructor autowiring all of the dependencies.
     *
     * @param userRepository - UserRepository userRepository.
     * @param projectService - ProjectService projectService.
     */
    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProjectService projectService) {
        this.projectService = projectService;
        mapper = UserMapper.INSTANCE;
        this.userRepository = userRepository;
        this.context = new CycleAvoidingMappingContext();

    }

    /**
     * LoadByUserName Method.
     *
     * @param name - String username.
     * @return - UserObject.
     * @throws UsernameNotFoundException - throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(name).orElse(null);

        if (user == null) {
            throw new UserNotFoundException(ValidationConstants.USER_NOT_FOUND);
        }
        if (!user.getStatus()) {
            throw new UserDisabledException(ValidationConstants.USER_IS_DISABLED + user.getUsername());
        }
        return user;
    }

    /**
     * @param bindingModel - User register binding model.
     * @return - returns the created user.
     */
    @Override
    public User register(RegisterBindingModel bindingModel) {
        User user = mapper.modelToUser(bindingModel, context);

        return this.userRepository.save(user);
    }

    @Override
    public User update(UserBindingModel bindingModel) {
        User user = mapper.modelToManager(bindingModel, context);

        return this.userRepository.save(user);
    }

    /**
     * @param role - The user role.
     * @return - A list of users with this role.
     */
    @Override
    public List<User> getUsersWithRole(String role) {

        return userRepository.getUserList(role);
    }

    /**
     * @param id - The user id.
     * @return - returns the user whit that id.
     */
    @Override
    public User getUserById(Long id) {

        Optional<User> userOptional = userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException(ExceptionMessageConstants.NO_SUCH_USER);
        }
        return userOptional.get();
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * @param username - The user username
     * @return - returns the user with that username.
     */
    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException(ExceptionMessageConstants.NO_SUCH_USER);
        }
        return userOptional.get();
    }

    /**
     * Gets user by username.
     * @param username - By which to search for.
     * @return - Mapped model object.
     */
    @Override
    public UserBindingModel getUserModelByUsername(String username) {
        User user = this.getUserByUsername(username);
        return mapper.userToModel(user, context);
    }

    /**
     * @param role - The role of the user.
     * @return - returns a list of user binding models with that role
     */
    @Override
    public List<UserBindingModel> getUserBindingModeByRole(String role) {
        List<UserBindingModel> userBindingModels = new ArrayList<>();

        List<User> users = this.getUsersWithRole(role);

        for (User user : users) {
            UserBindingModel model = mapper.userToModel(user, context);
            userBindingModels.add(model);
        }

        return userBindingModels;
    }

    /**
     * @param id - The user id.
     * @return - returns a user binding model according to the id.
     */
    @Override
    public UserBindingModel getUserBindingModelById(Long id) {

        User user = this.userRepository.findById(id).orElse(null);

        if (user == null) {
            throw new UserNotFoundException(ValidationConstants.USER_NOT_FOUND);
        }

        return mapper.userToModel(user, context);
    }

    @Override
    public List<UserBindingModel> getAllUserBindingModels() {
        List<UserBindingModel> userBindingModelList = new ArrayList<>();
        getAllUsers().forEach(user -> userBindingModelList.add(mapper.userToModel(user, context)));

        return userBindingModelList;
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<ProjectBindingModel> getAllUserProjects(Long id) {
        Set<TeamBindingModel> userTeams = getUserBindingModelById(id).getTeams();
        List<ProjectBindingModel> userProjectsList = new ArrayList<>();
        userTeams.forEach(team -> userProjectsList.add(projectService
                .getBindingProjectByTeamId(String.valueOf(team.getId()))));

        return userProjectsList;
    }

}
