package io.automationhacks.java9._01_http_client;

import org.testng.annotations.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

// Introduced in Java 9
// Source: https://www.baeldung.com/java-9-http-client
public class HttpClientTest {

    @Test
    public void testHttpClient() {

        try (var client = HttpClient.newHttpClient()) {
            var request =
                    HttpRequest.newBuilder()
                            .uri(URI.create("https://postman-echo.com/get"))
                            .build();

            client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenAccept(System.out::println)
                    .join();
        }
    }
}
