package com.homework.webserver.servlet;

import com.homework.webserver.crm.model.Address;
import com.homework.webserver.crm.model.Client;
import com.homework.webserver.crm.model.Phone;
import com.homework.webserver.services.DatabaseManager;
import com.homework.webserver.services.TemplateProcessor;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"java:S1989"})
public class ClientsServlet extends HttpServlet {
    private static final String PAGE_TEMPLATE = "clients.html";
    private static final String PARAM_NAME = "name";
    private static final String PARAM_ADDRESS = "address";
    private static final String PARAM_PHONE = "phone";
    private static final Logger log = LoggerFactory.getLogger(ClientsServlet.class);
    private final transient TemplateProcessor templateProcessor;
    private final transient DatabaseManager databaseManager;

    public ClientsServlet(TemplateProcessor templateProcessor) {
        this.templateProcessor = templateProcessor;
        this.databaseManager = new DatabaseManager();
    }

    private static boolean isStringEmpty(final String s) {
        return s == null || s.trim().isEmpty();
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
        if (!isStringEmpty(name) && !isStringEmpty(address) && !isStringEmpty(phone)) {
            insertClient(name, address, phone);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            log.error("Incorrect parameters, {}", request.getParameterMap());
        }

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

    private void insertClient(String name, String address, String phone) {
        databaseManager
                .getDbServiceClient()
                .saveClient(new Client(null, name, new Address(null, address), List.of(new Phone(null, phone))));
    }
}
