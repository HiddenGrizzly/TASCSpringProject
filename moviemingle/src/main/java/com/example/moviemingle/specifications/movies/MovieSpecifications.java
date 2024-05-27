package com.example.moviemingle.specifications.movies;

import com.example.moviemingle.entities.Director;
import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.entities.Actor;
import com.example.moviemingle.entities.Writer;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class MovieSpecifications {

    public static Specification<Movie> titleContainsIgnoreCase(String title) {
        return (root, query, criteriaBuilder) -> {
            String searchString = "%" + title.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("movieTitle")), searchString);
        };
    }

    public static Specification<Movie> actorContainsIgnoreCase(String actorName) {
        return (root, query, criteriaBuilder) -> {
            Join<Movie, Actor> actorJoin = root.join("actors");
            String searchString = "%" + actorName.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(actorJoin.get("actorName")), searchString);
        };
    }

    public static Specification<Movie> directorContainsIgnoreCase(String directorName) {
        return (root, query, criteriaBuilder) -> {
            Join<Movie, Director> directorJoin = root.join("directors");
            String searchString = "%" + directorName.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(directorJoin.get("directorName")), searchString);
        };
    }

    public static Specification<Movie> writerContainsIgnoreCase(String writerName) {
        return (root, query, criteriaBuilder) -> {
            Join<Movie, Writer> writerJoin = root.join("writers");
            String searchString = "%" + writerName.toLowerCase() + "%";
            return criteriaBuilder.like(criteriaBuilder.lower(writerJoin.get("writerName")), searchString);
        };
    }

    public static Specification<Movie> priceBetween(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            Path<Double> pricePath = root.get("price");
            return criteriaBuilder.between(pricePath, minPrice, maxPrice);
        };
    }

}
