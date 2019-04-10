// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.domain.Role;
import com.scalefocus.pms.models.binding.RoleBindingModel;

import java.util.Set;

public interface RoleService {

    Role getRoleByRoleName(String name);

    RoleBindingModel getRoleBindingByRoleName(String name);

    Role getRoleById(Long id);

    RoleBindingModel getRoleBindingById(Long id);

    Set<Role> getAllRoles();

    Set<RoleBindingModel> getAllRolesBinding();
}
