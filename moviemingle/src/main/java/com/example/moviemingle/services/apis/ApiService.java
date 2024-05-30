package com.example.moviemingle.services.apis;

import com.example.moviemingle.dtos.movies.MovieOmdbDTO;
import org.springframework.stereotype.Service;

@Service
public interface ApiService {
    MovieOmdbDTO getMovieApi(String title);
}
