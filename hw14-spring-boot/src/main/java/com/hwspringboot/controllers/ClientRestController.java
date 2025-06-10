package com.hwspringboot.controllers;

import com.hwspringboot.controllers.model.ClientCreatePayload;
import com.hwspringboot.model.Client;
import com.hwspringboot.services.ClientService;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientRestController {

    private final ClientService clientService;

    public ClientRestController(@Autowired ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/", produces = "application/json")
    public String clientsList() {
        List<Client> clients = clientService.findAll();
        return clients.stream().map(Client::toString).collect(Collectors.joining(", "));
    }

    @PostMapping("/create")
    public RedirectView createClient(@ModelAttribute ClientCreatePayload client) throws IOException {
        if (client == null || client.name() == null || client.address() == null || client.phone() == null) {
            throw new IllegalArgumentException("Invalid client data");
        }
        clientService.save(client);
        return new RedirectView("/clients");
    }
}
