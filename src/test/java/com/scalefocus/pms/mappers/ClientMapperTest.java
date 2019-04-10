package com.scalefocus.pms.mappers;

import com.scalefocus.pms.constants.TestConstants;
import com.scalefocus.pms.domain.Client;
import com.scalefocus.pms.models.binding.ClientBindingModel;
import com.scalefocus.pms.models.binding.ProjectBindingModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

public class ClientMapperTest {

    private ClientBindingModel bindingModel;
    private ClientMapper clientMapper;
    private CycleAvoidingMappingContext context;

    @Before
    public void setUp() {
        bindingModel = new ClientBindingModel();
        clientMapper = ClientMapper.INSTANCE;
        context = new CycleAvoidingMappingContext();
    }

    @Test
    public void modelToClientMapperTest() {
        Client client;
        List<ProjectBindingModel> projects = new ArrayList<>();
        bindingModel.setClientName(TestConstants.TEST_STRING);

        client = clientMapper.modelToClient(bindingModel, context);
        assertEquals(client.getClientName(),TestConstants.TEST_STRING);

    }

    @Test
    public void clientToModelMapperTest() {
        Client client = new Client(TestConstants.TEST_STRING);
        
        bindingModel.setClientName(TestConstants.TEST_STRING);
        assertEquals(client.getClientName(), bindingModel.getClientName());

    }
}