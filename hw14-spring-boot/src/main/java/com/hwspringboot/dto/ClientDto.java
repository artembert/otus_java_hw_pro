package com.hwspringboot.dto;

import com.hwspringboot.model.Address;
import com.hwspringboot.model.Client;
import com.hwspringboot.model.Phone;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Long id;

    private String name;

    private String address;

    private String phones;

    public ClientDto(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.address = client.getAddress() == null ? null : client.getAddress().street();
        this.phones = client.getPhones().stream().map(Phone::number).collect(Collectors.joining(", "));
    }

    public Client toClient() {
        Set<Phone> phoneSet = Arrays.stream(this.getPhones().split(",")).map(s -> new Phone(null, s.trim(), this.id))
            .collect(Collectors.toSet());
        return new Client(this.getId(), this.getName(), new Address(null, this.getAddress(), this.getId())
            , phoneSet);
    }
}