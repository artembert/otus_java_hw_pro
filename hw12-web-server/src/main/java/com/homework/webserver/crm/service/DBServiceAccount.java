package com.homework.webserver.crm.service;

public interface DBServiceAccount {

    boolean isPasswordValid(String email, String password);
}
