package com.hwspringboot.controllers;

import com.hwspringboot.model.Client;
import com.hwspringboot.services.ClientService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
