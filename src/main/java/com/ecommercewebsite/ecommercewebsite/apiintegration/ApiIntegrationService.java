package com.ecommercewebsite.ecommercewebsite.apiintegration;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Objects;

@Service
public class ApiIntegrationService {
    private final OkHttpClient client = new OkHttpClient();

    public String getUsers() throws IOException {
        Request request = new Request.Builder()
                .url("http://jsonplaceholder.typicode.com/users")
                .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        return response.body().string();
    }

    public String addUser(ApiIntegrationRequest apiIntegrationRequest) throws IOException {
        String API_URL = "http://jsonplaceholder.typicode.com/";
        String endpoint = API_URL + "users";
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "{\"name\": \"" + apiIntegrationRequest.getName() + "\","
                        + "\"username\": \"" + apiIntegrationRequest.getUsername() + "\","
                        + "\"email\": \"" + apiIntegrationRequest.getEmail() + "\"}"
        );
        Request request = new Request.Builder()
                .url(endpoint)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }


    public void deleteUser(String userId) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://jsonplaceholder.typicode.com/users/" + userId)
                .delete()
                .build();

        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            System.out.println("User with ID " + userId + " deleted successfully");
        } else {
            System.out.println("Error deleting user with ID " + userId + ": " + response.code() + " " + response.message());
        }
    }

    public String updateUser(String userId) throws IOException{
        OkHttpClient client = new OkHttpClient();
        String url = "http://jsonplaceholder.typicode.com/users/" + userId;
        String json = "{\"name\":\"John Doe\",\"username\":\"johndoe\",\"email\":\"johndoe@example.com\"}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"), json);
        Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return Objects.requireNonNull(response.body()).string();
        }
    }
}
