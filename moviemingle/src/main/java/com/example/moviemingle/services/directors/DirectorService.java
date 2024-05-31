package com.example.moviemingle.services.directors;

import com.example.moviemingle.entities.Director;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface DirectorService {
    Page<Director> findAllDirectors(Pageable pageable);

    public Director findDirectorById(Long id);

    public Director saveDirector(String directorName);

    Director updateDirector(Long id, String directorName);

    public void deleteDirector(Long id);

    Director findOrCreateDirector(String directorName);
}
