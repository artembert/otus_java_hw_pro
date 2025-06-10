package com.hwspringboot.services;

import com.hwspringboot.model.Address;
import com.hwspringboot.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(@Autowired AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address create(String street) {
        var address = new Address(null, street);
        return addressRepository.save(address);
    }
}
