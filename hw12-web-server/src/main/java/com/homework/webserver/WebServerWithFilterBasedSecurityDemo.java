package com.homework.webserver;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.homework.webserver.dao.InMemoryUserDao;
import com.homework.webserver.dao.UserDao;
import com.homework.webserver.server.UsersWebServer;
import com.homework.webserver.server.UsersWebServerWithFilterBasedSecurity;
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
        UserDao userDao = new InMemoryUserDao();
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);
        UserAuthService authService = new UserAuthServiceImpl(userDao);

        UsersWebServer usersWebServer = new UsersWebServerWithFilterBasedSecurity(
                WEB_SERVER_PORT, authService, userDao, gson, templateProcessor);

        usersWebServer.start();
        usersWebServer.join();
    }
}
