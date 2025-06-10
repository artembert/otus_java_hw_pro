package com.hwspringboot.services;

import com.hwspringboot.model.Client;
import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client findById(Long id);

    Client save(Client client);
}
