package com.homework.webserver;

import com.homework.webserver.crm.service.DBServiceAccount;
import com.homework.webserver.crm.service.DBServiceClient;
import com.homework.webserver.helpers.FileSystemHelper;
import com.homework.webserver.server.UsersWebServer;
import com.homework.webserver.server.UsersWebServerWithBasicSecurity;
import com.homework.webserver.services.DatabaseManager;
import com.homework.webserver.services.TemplateProcessor;
import com.homework.webserver.services.TemplateProcessorImpl;
import java.net.URI;
import org.eclipse.jetty.security.HashLoginService;
import org.eclipse.jetty.security.LoginService;
import org.eclipse.jetty.util.resource.PathResourceFactory;
import org.eclipse.jetty.util.resource.Resource;

/*
    Полезные для демо ссылки

    // Стартовая страница
    http://localhost:8080

    // Страница пользователей
    http://localhost:8080/users

    // REST сервис
    http://localhost:8080/api/user/3
*/
public class WebServerWithBasicSecurityDemo {
    private static final int WEB_SERVER_PORT = 8080;
    private static final String TEMPLATES_DIR = "/templates/";
    private static final String HASH_LOGIN_SERVICE_CONFIG_NAME = "realm.properties";
    private static final String REALM_NAME = "AnyRealm";

    public static void main(String[] args) throws Exception {
        TemplateProcessor templateProcessor = new TemplateProcessorImpl(TEMPLATES_DIR);

        String hashLoginServiceConfigPath =
                FileSystemHelper.localFileNameOrResourceNameToFullPath(HASH_LOGIN_SERVICE_CONFIG_NAME);
        PathResourceFactory pathResourceFactory = new PathResourceFactory();
        Resource configResource = pathResourceFactory.newResource(URI.create(hashLoginServiceConfigPath));

        UsersWebServer usersWebServer = getUsersWebServer(configResource, templateProcessor);
        usersWebServer.join();
    }

    private static UsersWebServer getUsersWebServer(Resource configResource, TemplateProcessor templateProcessor)
            throws Exception {
        LoginService loginService = new HashLoginService(REALM_NAME, configResource);
        var databaseManager = new DatabaseManager();
        DBServiceClient dbServiceClient = databaseManager.getDbServiceClient();
        DBServiceAccount dbServiceAccount = databaseManager.getDbServiceAccount();

        UsersWebServer usersWebServer = new UsersWebServerWithBasicSecurity(
                WEB_SERVER_PORT, loginService, templateProcessor, dbServiceClient, dbServiceAccount);

        usersWebServer.start();
        return usersWebServer;
    }
}
