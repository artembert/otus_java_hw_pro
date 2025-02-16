package com.homework.webserver.services;

import com.homework.webserver.core.repository.DataTemplateHibernate;
import com.homework.webserver.core.repository.HibernateUtils;
import com.homework.webserver.core.sessionmanager.TransactionManagerHibernate;
import com.homework.webserver.crm.dbmigrations.MigrationsExecutorFlyway;
import com.homework.webserver.crm.model.Address;
import com.homework.webserver.crm.model.Client;
import com.homework.webserver.crm.model.Phone;
import com.homework.webserver.crm.service.DBServiceClient;
import com.homework.webserver.crm.service.DbServiceClientImpl;
import lombok.Getter;
import org.hibernate.cfg.Configuration;

@Getter
public class DatabaseManager {
    public static final String HIBERNATE_CFG_FILE = "hibernate.cfg.xml";
    private final DBServiceClient dbServiceClient;

    public DatabaseManager() {
        var configuration = new Configuration().configure(HIBERNATE_CFG_FILE);

        var dbUrl = configuration.getProperty("hibernate.connection.url");
        var dbUserName = configuration.getProperty("hibernate.connection.username");
        var dbPassword = configuration.getProperty("hibernate.connection.password");

        new MigrationsExecutorFlyway(dbUrl, dbUserName, dbPassword).executeMigrations();

        var sessionFactory =
                HibernateUtils.buildSessionFactory(configuration, Client.class, Address.class, Phone.class);

        var transactionManager = new TransactionManagerHibernate(sessionFactory);
        ///
        var clientTemplate = new DataTemplateHibernate<>(Client.class);
        ///
        dbServiceClient = new DbServiceClientImpl(transactionManager, clientTemplate);
    }
}
