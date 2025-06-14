package com.hwspringboot.model;

import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "address")
public record Address(@Id Long id, @NonNull String street, Long clientId) {
}
