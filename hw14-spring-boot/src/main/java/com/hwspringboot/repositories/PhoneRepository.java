package com.hwspringboot.repositories;

import com.hwspringboot.model.Phone;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, Long> {

    Optional<Phone> findById(Long id);

    Phone save(Phone address);
}
