package com.hwspringboot.services;

import com.hwspringboot.controllers.model.ClientCreatePayload;
import com.hwspringboot.model.Client;
import java.util.List;

public interface ClientService {
    List<Client> findAll();

    Client save(ClientCreatePayload client);
}
