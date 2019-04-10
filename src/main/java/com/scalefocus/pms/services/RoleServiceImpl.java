// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.domain.Role;
import com.scalefocus.pms.mappers.CycleAvoidingMappingContext;
import com.scalefocus.pms.mappers.RoleMapper;
import com.scalefocus.pms.models.binding.RoleBindingModel;
import com.scalefocus.pms.repositories.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final CycleAvoidingMappingContext context;
    private final RoleMapper mapper;

    /**
     * Autowired constructor taking one parameter.
     *
     * @param roleRepository - RoleRepository roleRepository.
     */
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.context = new CycleAvoidingMappingContext();
        this.mapper = RoleMapper.INSTANCE;
    }

    @Override
    public Role getRoleByRoleName(String name) {
        return roleRepository.findRoleByAuthorityEquals(name);
    }

    @Override
    public RoleBindingModel getRoleBindingByRoleName(String name) {
        return mapper.roleToRoleBindingModel(getRoleByRoleName(name), context);
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public RoleBindingModel getRoleBindingById(Long id) {
        return mapper.roleToRoleBindingModel(getRoleById(id), context);
    }

    @Override
    public Set<Role> getAllRoles() {

        return new HashSet<>(roleRepository.findAll());
    }

    @Override
    public Set<RoleBindingModel> getAllRolesBinding() {
        Set<RoleBindingModel> rolesBindingSet = new HashSet<>();
        getAllRoles().forEach(role -> rolesBindingSet.add(mapper.roleToRoleBindingModel(role, context)));
        return rolesBindingSet;
    }


}
