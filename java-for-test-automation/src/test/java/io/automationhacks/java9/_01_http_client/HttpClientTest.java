package io.automationhacks.java9._01_http_client;

import static com.google.common.truth.Truth.assertWithMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Introduced in Java 9
// Source: https://www.baeldung.com/java-9-http-client
public class HttpClientTest {

    private final Logger logger = LoggerFactory.getLogger(HttpClientTest.class);

    @Test
    public void testHttpClient() {

        try (var client = HttpClient.newHttpClient()) {
            var request =
                    HttpRequest.newBuilder()
                            .uri(URI.create("https://postman-echo.com/get"))
                            .build();

            var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            logger.info("Response: %s".formatted(response));

            assertWithMessage("Echo API did not return a response").that(response).isNotNull();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
