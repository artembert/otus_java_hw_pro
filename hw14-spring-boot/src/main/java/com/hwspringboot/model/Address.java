package com.hwspringboot.model;

//import lombok.Getter;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.annotation.PersistenceCreator;
//import org.springframework.data.annotation.Transient;
//import org.springframework.data.domain.Persistable;
//import org.springframework.data.relational.core.mapping.Column;
//import org.springframework.data.relational.core.mapping.Table;
//
//@Getter
//@Table(name = "address")
//public class Address implements Persistable<Long> {
//    @Id
//    @Column("id")
//    public final Long id;
//
//    private final String street;
//
//    @Transient
//    private final boolean isNew;
//
//    public Address(Long id, String street, boolean isNew) {
//        this.id = id;
//        this.street = street;
//        this.isNew = isNew;
//    }
//
//    @PersistenceCreator
//    public Address(Long id, String street) {
//        this(id, street, true);
//    }
//
//    @Override
//    public boolean isNew() {
//        return isNew;
//    }
//}

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("address")
public record Address(
    @Id @Column("id") Long id,
    String street
) {
}
