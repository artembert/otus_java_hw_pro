package com.homework.webserver.servlet;

import com.google.gson.Gson;
import com.homework.webserver.dao.UserDao;
import com.homework.webserver.services.DatabaseManager;
import com.homework.webserver.services.TemplateProcessor;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings({"java:S1989"})
public class ClientsServlet extends HttpServlet {
    private static final String PAGE_TEMPLATE = "clients.html";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_ADDRESS = "address";
    private static final String PARAM_PHONE = "phone";

    private final transient TemplateProcessor templateProcessor;
    private final DatabaseManager databaseManager;

    public ClientsServlet(TemplateProcessor templateProcessor, UserDao userDao, Gson gson) {
        this.templateProcessor = templateProcessor;
        this.databaseManager = new DatabaseManager();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(PAGE_TEMPLATE, getParamsMap()));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter(PARAM_NAME);
        String address = request.getParameter(PARAM_ADDRESS);
        String phone = request.getParameter(PARAM_PHONE);
        System.out.println(name + " " + address + " " + phone);

        response.setContentType("text/html");
        response.getWriter().println(templateProcessor.getPage(PAGE_TEMPLATE, getParamsMap()));
    }

    private HashMap<String, Object> getParamsMap() {
        HashMap<String, Object> paramsMap = new HashMap<>();
        var clients = databaseManager.getDbServiceClient().findAll();
        if (clients != null) {
            paramsMap.put("clients", clients);
        }
        return paramsMap;
    }
}
