package com.hwspringboot.repositories;

import com.hwspringboot.model.Client;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, Long> {
    Optional<Client> findById(Long id);

    <S extends Client> S save(S entity);

    Iterable<Client> findAll();
}
