package com.homework.webserver.servlet;

import com.google.gson.Gson;
import com.homework.webserver.crm.model.Address;
import com.homework.webserver.crm.model.Client;
import com.homework.webserver.crm.model.Phone;
import com.homework.webserver.dao.UserDao;
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

    private final transient UserDao userDao;
    private final transient Gson gson;
    private final transient TemplateProcessor templateProcessor;

    public ClientsServlet(TemplateProcessor templateProcessor, UserDao userDao, Gson gson) {
        this.templateProcessor = templateProcessor;
        this.userDao = userDao;
        this.gson = gson;
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
        paramsMap.put("clients", List.of(new Client(1L, "John Show", new Address(1L, "Winterfell, The North, Westeros"),
            List.of(new Phone(1L, "+123 456 78 90"), new Phone(1L, "+413 419 923 12 42")))
        ));
        return paramsMap;
    }
}
