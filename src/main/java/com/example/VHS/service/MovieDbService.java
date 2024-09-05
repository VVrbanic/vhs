package com.example.VHS.service;

import com.example.VHS.entity.MovieDTO;
import com.example.VHS.entity.Vhs;
import com.example.VHS.repository.VhsRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class MovieDbService {

    private final OkHttpClient client;

    @Autowired
    private VhsService vhsService;

    public MovieDbService() {
        this.client = new OkHttpClient();
    }

    public String authenticate() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/authentication")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3YzBhOGI0YjgwNTk5OTVlMTM1MWM1MTdiYTM1MmFlZSIsIm5iZiI6MTcyNTM2NDYwNy4zNjY2NjcsInN1YiI6IjY2ZDZmNGIzODYzYzNjODZkNTBkYjg0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WxwfgarJkNxPHUDz2mMUDrR9I-UE-O0oAgxzaGZovIo")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public String getPopular() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/trending/movie/week?language=en-US")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3YzBhOGI0YjgwNTk5OTVlMTM1MWM1MTdiYTM1MmFlZSIsIm5iZiI6MTcyNTM2NDYwNy4zNjY2NjcsInN1YiI6IjY2ZDZmNGIzODYzYzNjODZkNTBkYjg0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WxwfgarJkNxPHUDz2mMUDrR9I-UE-O0oAgxzaGZovIo")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            return response.body().string();
        }
    }

    public Map<String, String> getPopularWeHave() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/trending/movie/week?language=en-US")
                .get()
                .addHeader("accept", "application/json")
                .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI3YzBhOGI0YjgwNTk5OTVlMTM1MWM1MTdiYTM1MmFlZSIsIm5iZiI6MTcyNTM2NDYwNy4zNjY2NjcsInN1YiI6IjY2ZDZmNGIzODYzYzNjODZkNTBkYjg0ZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.WxwfgarJkNxPHUDz2mMUDrR9I-UE-O0oAgxzaGZovIo")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }

            String responseBody = response.body().string();

            JSONObject jsonResponse = new JSONObject(responseBody);

            JSONArray results = jsonResponse.getJSONArray("results");

            System.out.println(results);

            List<Vhs> vhsList = vhsService.getAllVhs();
            System.out.println(vhsList);

            Map<String, String> map = new HashMap<>();

            //Iterate over each movie in the results array
            for (int i = 0; i < results.length(); i++) {
                JSONObject movie = results.getJSONObject(i);
                String originalTitle = movie.getString("original_title");
                String overview = movie.getString("overview");

                //Check if the movie is in the database
                if(vhsList.stream().anyMatch(vhsValid -> vhsValid.getName().equalsIgnoreCase(originalTitle))){
                    // Print or store the extracted data
                    map.put(originalTitle, overview);
                }

            }

            return map;
        }
    }


}
