package com.homework.webserver;

import com.homework.webserver.crm.service.DBServiceClient;
import com.homework.webserver.server.UsersWebServer;
import com.homework.webserver.server.UsersWebServerSimple;
import com.homework.webserver.services.DatabaseManager;
import com.homework.webserver.services.TemplateProcessor;
import com.homework.webserver.services.TemplateProcessorImpl;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServerSimpleDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";

    public static void main(String[] args) throws Exception {
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        var databaseManager = new DatabaseManager();
        DBServiceClient dbServiceClient = databaseManager.getDbServiceClient();

        UsersWebServer usersWebServer = new UsersWebServerSimple(WEB_SERVER_PORT, templateProcessor, dbServiceClient);

        usersWebServer.start();
        usersWebServer.join();
    }
}
