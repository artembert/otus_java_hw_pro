package com.homework.webserver;

import static com.homework.webserver.utils.WebServerHelper.buildUrl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.google.gson.GsonBuilder;
import com.homework.webserver.crm.service.DBServiceAccount;
import com.homework.webserver.crm.service.DBServiceClient;
import com.homework.webserver.server.UsersWebServer;
import com.homework.webserver.server.UsersWebServerSimple;
import com.homework.webserver.services.TemplateProcessor;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тест сервера должен ")
class UsersWebServerImplTest {

    private static final int WEB_SERVER_PORT = 8989;
    private static final String WEB_SERVER_URL = "http://localhost:" + WEB_SERVER_PORT + "/";
    private static final String CLIENTS_URL = "clients";

    private static UsersWebServer webServer;
    private static HttpClient http;

    @BeforeAll
    static void setUp() throws Exception {
        http = HttpClient.newHttpClient();

        TemplateProcessor templateProcessor = mock(TemplateProcessor.class);
        DBServiceClient dbServiceClient = mock(DBServiceClient.class);
        DBServiceAccount dbServiceAccount = mock(DBServiceAccount.class);

        webServer = new UsersWebServerSimple(WEB_SERVER_PORT, templateProcessor, dbServiceClient, dbServiceAccount);
        webServer.start();
    }

    @AfterAll
    static void tearDown() throws Exception {
        webServer.stop();
    }

    @DisplayName("возвращать корректные данные при запросе пользователя по id если вход выполнен")
    @Test
    void shouldReturnCorrectUserWhenAuthorized() throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .GET()
            .uri(URI.create(buildUrl(WEB_SERVER_URL, CLIENTS_URL)))
            .build();
        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_OK);
        assertThat(response.headers().map().get("Content-Type")).isEqualTo(List.of("text/html;charset=utf-8"));
    }
}
