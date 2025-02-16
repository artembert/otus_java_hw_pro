package com.homework.webserver.crm.service;

import com.homework.webserver.core.repository.DataTemplate;
import com.homework.webserver.core.sessionmanager.TransactionManager;
import com.homework.webserver.crm.model.Account;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbServiceAccountImpl implements DBServiceAccount {
    private static final Logger log = LoggerFactory.getLogger(DbServiceAccountImpl.class);

    private final DataTemplate<Account> accountDataTemplate;
    private final TransactionManager transactionManager;

    public DbServiceAccountImpl(TransactionManager transactionManager, DataTemplate<Account> accountDataTemplate) {
        this.transactionManager = transactionManager;
        this.accountDataTemplate = accountDataTemplate;
    }

    @Override
    public boolean isPasswordValid(String email, String password) {
        return Optional.ofNullable(transactionManager.doInReadOnlyTransaction(session -> {
                var result = accountDataTemplate
                    .findByEntityField(session, "email", email);
                if (result.isEmpty()) {
                    return false;
                }
                log.info("account found: {}", result.getFirst());
                return result.getFirst().getPassword().equals(password);
            }))
            .orElse(false);
    }
}
