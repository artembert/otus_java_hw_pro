package com.homework.webserver;

import com.homework.webserver.crm.service.DBServiceAccount;
import com.homework.webserver.crm.service.DBServiceClient;
import com.homework.webserver.server.UsersWebServer;
import com.homework.webserver.server.UsersWebServerWithFilterBasedSecurity;
import com.homework.webserver.services.DatabaseManager;
import com.homework.webserver.services.TemplateProcessor;
import com.homework.webserver.services.TemplateProcessorImpl;
import com.homework.webserver.services.UserAuthService;
import com.homework.webserver.services.UserAuthServiceImpl;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServerWithFilterBasedSecurityDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        var databaseManager = new DatabaseManager();
        DBServiceClient dbServiceClient = databaseManager.getDbServiceClient();
        DBServiceAccount dbServiceAccount = databaseManager.getDbServiceAccount();
        UserAuthService authService = new UserAuthServiceImpl(dbServiceAccount);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(
                WEB_SERVER_PORT, authService, templateProcessor, dbServiceClient, dbServiceAccount);

        usersWebServer.start();
        usersWebServer.join();
    }
}
