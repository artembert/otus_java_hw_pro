package com.hwspringboot.model;

import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "phone")
public record Phone(@Id Long id, @NonNull String number, Long clientId) {}
