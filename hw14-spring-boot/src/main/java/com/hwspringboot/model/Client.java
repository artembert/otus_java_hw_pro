package com.hwspringboot.model;

import jakarta.annotation.Nonnull;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table(name = "client")
public class Client implements Persistable<Long> {

    private final String name;
    @Transient
    private final boolean isNew;
    @Id
    @Nonnull
    private Long id;
    @MappedCollection(idColumn = "id")
    private Address address;

    @MappedCollection(idColumn = "client_id", keyColumn = "id")
    private List<Phone> phones;

    public Client(String name) {
        this.id = null;
        this.name = name;
        this.isNew = true;
    }

    public Client(Long id, String name, boolean isNew) {
        this.id = id;
        this.name = name;
        this.isNew = isNew;
    }

    public Client(Long id, String name, Address address, List<Phone> phones, Boolean isNew) {
        this.id = id;
        this.name = name;
        this.address = new Address(null, address.street());
        this.phones = phones.stream()
            .map(phone -> new Phone(phone.getId(), phone.getNumber(), id, false))
                .toList();
        this.isNew = isNew;
    }

    @PersistenceCreator
    public Client(Long id, String name, Address address) {
        this(id, name, address, List.of(), false);
    }

    public String toString() {
        return "Client{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @Override
    public @NonNull Long getId() {
        return id;
    }
}
