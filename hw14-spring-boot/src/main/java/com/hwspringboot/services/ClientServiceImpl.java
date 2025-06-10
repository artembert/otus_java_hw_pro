package com.hwspringboot.services;

import com.hwspringboot.controllers.model.ClientCreatePayload;
import com.hwspringboot.model.Client;
import com.hwspringboot.repositories.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final AddressService addressService;
    private final PhoneService phoneService;

    public ClientServiceImpl(@Autowired ClientRepository clientRepository, @Autowired AddressService addressService,
                             @Autowired PhoneService phoneService) {
        this.clientRepository = clientRepository;
        this.addressService = addressService;
        this.phoneService = phoneService;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client save(ClientCreatePayload payload) {
        var address = addressService.create(payload.address());
        var phone = phoneService.create(payload.phone(), address.id());
        var client = new Client(
            null,
            payload.name(),
            address,
            List.of(phone),
            true);
        return clientRepository.save(client);
    }
}
