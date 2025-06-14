package com.hwspringboot.services;

import com.hwspringboot.model.Client;
import com.hwspringboot.repositories.ClientRepository;
import java.util.List;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(@Autowired ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Client> findAll() {
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
            .toList();
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
