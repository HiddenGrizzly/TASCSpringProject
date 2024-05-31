package com.example.moviemingle.controllers.movies;

import com.example.moviemingle.dtos.movies.MovieOmdbDTO;
import com.example.moviemingle.services.apis.ApiService;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("movie-apis")
public class MovieApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping
    public MovieOmdbDTO getMovieFromApi(@RequestParam String title) throws IOException {
        return apiService.getMovieApi(title);
    }
}
