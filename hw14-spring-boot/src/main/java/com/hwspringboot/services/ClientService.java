package com.hwspringboot.services;

import com.hwspringboot.model.Client;
import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client save(Client client);
}
