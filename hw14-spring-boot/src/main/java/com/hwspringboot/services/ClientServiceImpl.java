package com.hwspringboot.services;

import com.hwspringboot.controllers.model.ClientCreatePayload;
import com.hwspringboot.model.Address;
import com.hwspringboot.model.Client;
import com.hwspringboot.model.Phone;
import com.hwspringboot.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final AddressService addressService;

    public ClientServiceImpl(@Autowired ClientRepository clientRepository, @Autowired AddressService addressService) {
        this.clientRepository = clientRepository;
        this.addressService = addressService;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client save(ClientCreatePayload payload) {
        var address = new Address(null, payload.address(), true);
        var phone = new Phone(null, payload.phone(), null, true);
        var client = new Client(
            null,
            payload.name(),
            address,
            List.of(phone),
            true);
        return clientRepository.save(client);
    }
}
