package com.homework.webserver.server;

import com.google.gson.Gson;
import com.homework.webserver.dao.UserDao;
import com.homework.webserver.services.TemplateProcessor;
import com.homework.webserver.services.UserAuthService;
import com.homework.webserver.servlet.AuthorizationFilter;
import com.homework.webserver.servlet.LoginServlet;
import java.util.Arrays;
import org.eclipse.jetty.ee10.servlet.FilterHolder;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.servlet.ServletHolder;
import org.eclipse.jetty.server.Handler;

public class UsersWebServerWithFilterBasedSecurity extends UsersWebServerSimple {
    private final UserAuthService authService;

    public UsersWebServerWithFilterBasedSecurity(
            int port, UserAuthService authService, UserDao userDao, Gson gson, TemplateProcessor templateProcessor) {
        super(port, userDao, gson, templateProcessor);
        this.authService = authService;
    }

    @Override
    protected Handler applySecurity(ServletContextHandler servletContextHandler, String... paths) {
        servletContextHandler.addServlet(new ServletHolder(new LoginServlet(templateProcessor, authService)), "/login");
        AuthorizationFilter authorizationFilter = new AuthorizationFilter();
        Arrays.stream(paths)
                .forEachOrdered(
                        path -> servletContextHandler.addFilter(new FilterHolder(authorizationFilter), path, null));
        return servletContextHandler;
    }
}
