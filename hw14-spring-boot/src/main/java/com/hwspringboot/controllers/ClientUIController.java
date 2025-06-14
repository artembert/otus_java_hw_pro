package com.hwspringboot.controllers;

import com.hwspringboot.dto.ClientCreatePayload;
import com.hwspringboot.dto.ClientDto;
import com.hwspringboot.model.Client;
import com.hwspringboot.services.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ClientUIController {

    private final ClientService clientService;

    public ClientUIController(@Autowired ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/clients")
    public String clientsList(Model model) {
        List<Client> clients = clientService.findAll();
        model.addAttribute("clients", clients);
        return "clients";
    }

    @PostMapping("/clients")
    public RedirectView createClient(@ModelAttribute ClientCreatePayload client) {
        if (client == null || client.name() == null || client.address() == null || client.phone() == null) {
            throw new IllegalArgumentException("Invalid client data");
        }
        var clientDto = new ClientDto(null, client.name(), client.address(), client.phone());
        clientService.save(clientDto.toClient());
        return new RedirectView("/clients");
    }
}
