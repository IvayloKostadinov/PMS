// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.services;

import com.scalefocus.pms.domain.Client;
import com.scalefocus.pms.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    /**
     * @param clientRepository - The repository for the Client entity.
     */
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    /**
     * This method find an existing or creates a new Client.
     *
     * @param clientName - The name of the client.
     * @return - Returns an object of type Client.
     */
    @Override
    public Client createClient(String clientName) {
        Client client = clientRepository.findByClientName(clientName);

        if (client == null) {

            client = new Client();
            client.setClientName(clientName);
            clientRepository.saveAndFlush(client);
        }

        return client;

    }

}
