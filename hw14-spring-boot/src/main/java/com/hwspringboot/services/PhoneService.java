package com.hwspringboot.services;

import com.hwspringboot.model.Phone;
import com.hwspringboot.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PhoneService {
    private final PhoneRepository phoneRepository;

    public PhoneService(@Autowired PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public Phone create(String number, Long clientId) {
        var phone = new Phone(null, number, clientId);
        return phoneRepository.save(phone);
    }
}
