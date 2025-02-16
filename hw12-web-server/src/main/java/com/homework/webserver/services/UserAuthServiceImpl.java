package com.homework.webserver.services;

import com.homework.webserver.crm.service.DBServiceAccount;

public class UserAuthServiceImpl implements UserAuthService {

    private final DBServiceAccount dbServiceAccount;

    public UserAuthServiceImpl(DBServiceAccount dbServiceAccount) {
        this.dbServiceAccount = dbServiceAccount;
    }

    @Override
    public boolean authenticate(String email, String password) {
        return dbServiceAccount.isPasswordValid(email, password);
    }
}
