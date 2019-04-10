package com.scalefocus.pms.services;

import com.scalefocus.pms.constants.FieldConstants;
import com.scalefocus.pms.domain.Client;
import com.scalefocus.pms.repositories.ClientRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

public class ClientServiceImplTest {

    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        clientService = new ClientServiceImpl(clientRepository);

    }

    @Test
    public void createClientTest() {
        Client client = clientService.createClient(FieldConstants.BINDING_MODEL_STRING);
        String clientName = client.getClientName();
        assertEquals(clientName, FieldConstants.BINDING_MODEL_STRING);
    }
}