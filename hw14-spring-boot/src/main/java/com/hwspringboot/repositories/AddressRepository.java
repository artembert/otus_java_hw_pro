package com.hwspringboot.repositories;

import com.hwspringboot.model.Address;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {

    Optional<Address> findById(Long id);

    Address save(Address address);
}
