package com.hwspringboot.repositories;

import com.hwspringboot.model.Client;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {

    Optional<Client> findById(Long id);

    Client save(Client client);

    List<Client> findAll();
}
