package com.hwspringboot.model;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Table(name = "phone")
public class Phone implements Persistable<Long> {
    @Id
    @Column("id")
    public final Long id;
    public final Long clientId;
    private final String number;
    @Transient
    private final boolean isNew;

    public Phone(Long id, String number, Long clientId, boolean isNew) {
        this.id = id;
        this.number = number;
        this.clientId = clientId;
        this.isNew = isNew;
    }

    @PersistenceCreator
    public Phone(Long id, String number, Long clientId) {
        this(id, number, clientId, false);
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
