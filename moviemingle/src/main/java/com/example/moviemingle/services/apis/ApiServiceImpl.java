package com.example.moviemingle.services.apis;

import com.example.moviemingle.dtos.movies.MovieOmdbDTO;
import com.example.moviemingle.exceptions.NotfoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;


@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private OkHttpClient http;

    @Autowired
    private ObjectMapper mapper;

    @Value("${omdb.endpoint}")
    private String apiEndpoint;

    @SneakyThrows
    @Override
    public MovieOmdbDTO getMovieApi(String title) {
        Request req = new Request.Builder().url(apiEndpoint+"t="+title).build();
        try (Response response = http.newCall(req).execute()) {
            if (response.isSuccessful()) {
                MovieOmdbDTO movieRes = mapper.readValue(response.body().string(), MovieOmdbDTO.class);
                if(!movieRes.getResponse()){
                    throw new NotfoundException("Movie not found on OMDB");
                }
                return movieRes;
            } else {
                throw new IOException("Unexpected response: " + response.code());
            }
        }
    }
}
