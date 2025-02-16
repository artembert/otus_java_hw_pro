package com.homework.webserver;

import static com.homework.webserver.utils.WebServerHelper.buildUrl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.homework.webserver.crm.service.DBServiceAccount;
import com.homework.webserver.crm.service.DBServiceClient;
import com.homework.webserver.dao.UserDao;
import com.homework.webserver.model.User;
import com.homework.webserver.server.UsersWebServer;
import com.homework.webserver.server.UsersWebServerSimple;
import com.homework.webserver.services.TemplateProcessor;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Тест сервера должен ")
class UsersWebServerImplTest {

    private static final int WEB_SERVER_PORT = 8989;
    private static final String WEB_SERVER_URL = "http://localhost:" + WEB_SERVER_PORT + "/";
    private static final String API_USER_URL = "api/user";

    private static final long DEFAULT_USER_ID = 1L;

    private static final User DEFAULT_USER = new User(DEFAULT_USER_ID, "Vasya", "user1", "11111");

    private static Gson gson;
    private static UsersWebServer webServer;
    private static HttpClient http;

    @BeforeAll
    static void setUp() throws Exception {
        http = HttpClient.newHttpClient();

        TemplateProcessor templateProcessor = mock(TemplateProcessor.class);
        DBServiceClient dbServiceClient = mock(DBServiceClient.class);
        DBServiceAccount dbServiceAccount = mock(DBServiceAccount.class);
        UserDao userDao = mock(UserDao.class);

        given(userDao.findById(DEFAULT_USER_ID)).willReturn(Optional.of(DEFAULT_USER));

        gson = new GsonBuilder().serializeNulls().create();
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
                .uri(URI.create(buildUrl(WEB_SERVER_URL, API_USER_URL, String.valueOf(DEFAULT_USER_ID))))
                .build();
        HttpResponse<String> response = http.send(request, HttpResponse.BodyHandlers.ofString());

        assertThat(response.statusCode()).isEqualTo(HttpURLConnection.HTTP_OK);
        assertThat(response.body()).isEqualTo(gson.toJson(DEFAULT_USER));
    }
}
