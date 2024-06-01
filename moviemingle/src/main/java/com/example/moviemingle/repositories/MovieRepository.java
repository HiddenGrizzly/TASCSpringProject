package com.example.moviemingle.repositories;

import com.example.moviemingle.entities.Movie;
import com.example.moviemingle.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    boolean existsByMovieTitle(String movieTitle);
    
    List<Movie> findByIdIn(Collection<Long> ids);
    
    Page<Movie> findByUserMovies_User(User user, Pageable pageable);
    
}
