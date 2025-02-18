package com.homework.webserver.services;

public interface UserAuthService {
    boolean authenticate(String login, String password);
}
