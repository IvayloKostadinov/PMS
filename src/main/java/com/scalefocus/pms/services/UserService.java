// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.domain.User;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import com.scalefocus.pms.models.binding.RegisterBindingModel;
import com.scalefocus.pms.models.binding.UserBindingModel;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User register(RegisterBindingModel bindingModel);

    User update(UserBindingModel bindingModel);

    List<User> getUsersWithRole(String role);

    User getUserById(Long id);

    List<User> getAllUsers();

    User getUserByUsername(String username);

    UserBindingModel getUserModelByUsername(String username);

    List<UserBindingModel> getUserBindingModeByRole(String role);

    UserBindingModel getUserBindingModelById(Long id);

    List<UserBindingModel> getAllUserBindingModels();

    void deleteUserById(Long id);

    List<ProjectBindingModel> getAllUserProjects(Long id);


}
