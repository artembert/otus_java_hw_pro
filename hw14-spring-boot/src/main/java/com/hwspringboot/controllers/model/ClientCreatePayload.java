package com.hwspringboot.controllers.model;

public record ClientCreatePayload(
    String name,
    String address,
    String phone
) {
}
