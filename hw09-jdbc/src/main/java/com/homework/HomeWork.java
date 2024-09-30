package com.homework;

import com.homework.core.repository.executor.DbExecutorImpl;
import com.homework.core.sessionmanager.TransactionRunnerJdbc;
import com.homework.crm.datasource.DriverManagerDataSource;
import com.homework.crm.model.Client;
import com.homework.crm.model.Manager;
import com.homework.jdbc.mapper.*;
import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.sql.DataSource;

@SuppressWarnings({"java:S125", "java:S1481"})
public class HomeWork {
    private static final String URL = "jdbc:postgresql://localhost:5430/demoDB";
    private static final String USER = "usr";
    private static final String PASSWORD = "pwd";

    private static final Logger log = LoggerFactory.getLogger(HomeWork.class);

    public static void main(String[] args) {
        // Общая часть
        var dataSource = new DriverManagerDataSource(URL, USER, PASSWORD);
        flywayMigrations(dataSource);
        var transactionRunner = new TransactionRunnerJdbc(dataSource);
        var dbExecutor = new DbExecutorImpl();

        // Работа с клиентом
        EntityClassMetaData<Client> entityClassMetaDataClient = new EntityClassMetaDataImpl<>(Client.class);
        log.info(
                "[Client]: {}, {}",
                entityClassMetaDataClient.getName(),
                entityClassMetaDataClient.getIdField().getName());
        EntitySQLMetaData entitySQLMetaDataClient = new EntitySQLMetaDataImpl<>(entityClassMetaDataClient);
        var dataTemplateClient = new DataTemplateJdbc<Client>(
                dbExecutor, entitySQLMetaDataClient); // реализация DataTemplate, универсальная

        // Код дальше должен остаться
        //        var dbServiceClient = new DbServiceClientImpl(transactionRunner, dataTemplateClient);
        //        dbServiceClient.saveClient(new Client("dbServiceFirst"));
        //
        //        var clientSecond = dbServiceClient.saveClient(new Client("dbServiceSecond"));
        //        var clientSecondSelected = dbServiceClient
        //                .getClient(clientSecond.getId())
        //                .orElseThrow(() -> new RuntimeException("Client not found, id:" + clientSecond.getId()));
        //        log.info("clientSecondSelected:{}", clientSecondSelected);

        // Сделайте тоже самое с классом Manager (для него надо сделать свою таблицу)

        EntityClassMetaData<Manager> entityClassMetaDataManager = new EntityClassMetaDataImpl<>(Manager.class);
        log.info(
                "[Manager]: {}, {}",
                entityClassMetaDataManager.getName(),
                entityClassMetaDataManager.getIdField().getName());
        EntitySQLMetaData entitySQLMetaDataManager = null; // = new EntitySQLMetaDataImpl(entityClassMetaDataManager);
        var dataTemplateManager = new DataTemplateJdbc<Manager>(dbExecutor, entitySQLMetaDataManager);

        //        var dbServiceManager = new DbServiceManagerImpl(transactionRunner, dataTemplateManager);
        //        dbServiceManager.saveManager(new Manager("ManagerFirst"));
        //
        //        var managerSecond = dbServiceManager.saveManager(new Manager("ManagerSecond"));
        //        var managerSecondSelected = dbServiceManager
        //                .getManager(managerSecond.getNo())
        //                .orElseThrow(() -> new RuntimeException("Manager not found, id:" + managerSecond.getNo()));
        //        log.info("managerSecondSelected:{}", managerSecondSelected);
    }

    private static void flywayMigrations(DataSource dataSource) {
        log.info("db migration started...");
        var flyway = Flyway.configure()
                .dataSource(dataSource)
                .locations("classpath:/db/migration")
                .load();
        flyway.migrate();
        log.info("db migration finished.");
        log.info("***");
    }
}
