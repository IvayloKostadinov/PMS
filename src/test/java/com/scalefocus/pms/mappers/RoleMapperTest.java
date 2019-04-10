package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Role;
import com.scalefocus.pms.models.binding.RoleBindingModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoleMapperTest {

    private RoleBindingModel bindingModel;
    private RoleMapper roleMapper;
    private CycleAvoidingMappingContext context;

    @Before
    public void setUp() throws Exception {
        bindingModel = new RoleBindingModel();
        roleMapper = RoleMapper.INSTANCE;
        context= new CycleAvoidingMappingContext();
    }

    @Test
    public void roleToModelTest() {
        Role role = new Role();
        role.setAuthority(TestConstants.TEST_STRING_ADMIN);

        bindingModel = roleMapper.roleToRoleBindingModel(role,context);

        assertEquals(bindingModel.getAuthority(), TestConstants.TEST_STRING_ADMIN);
    }

    @Test
    public void modelToRoleTest() {
        bindingModel.setAuthority(TestConstants.TEST_STRING_ADMIN);

        Role role = roleMapper.roleBindingModel(bindingModel,context);

        assertEquals(role.getAuthority(), TestConstants.TEST_STRING_ADMIN);
    }
}