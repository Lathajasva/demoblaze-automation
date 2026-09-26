package com.demoblaze.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DemoBlazeApiTest {

    private final HttpClient client = HttpClient.newHttpClient();

    @Test
    public void verifyProductsApi() throws Exception {

        String url = "https://api.demoblaze.com/entries";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("GET API Status Code: " + response.statusCode());

        Assert.assertEquals(response.statusCode(), 200,
                "Products API should return HTTP 200");

        Assert.assertTrue(response.body().contains("Items"),
                "API response should contain Items");
    }

    @Test
    public void verifyLoginApi() throws Exception {

        String url = "https://api.demoblaze.com/login";

        String requestBody = """
                {
                    "username": "testuser",
                    "password": "testpassword"
                }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("POST Login API Status Code: " + response.statusCode());
        System.out.println("POST Login API Response: " + response.body());

        Assert.assertEquals(response.statusCode(), 200,
                "Login API should return HTTP 200");

        Assert.assertNotNull(response.body(),
                "Login API response should not be null");
    }
}