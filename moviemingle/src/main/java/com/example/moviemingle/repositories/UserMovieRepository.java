package com.example.moviemingle.repositories;

import com.example.moviemingle.entities.UserMovie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovie, Long> {


}
