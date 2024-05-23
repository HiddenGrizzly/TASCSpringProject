package com.example.moviemingle.services.directors;

import com.example.moviemingle.entities.Director;
import com.example.moviemingle.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    private DirectorRepository directorRepository;

    public Page<Director> findAllDirectors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return directorRepository.findAll(pageable);
    }

    public Director findDirectorById(Long id) {
        return directorRepository.findById(id).orElse(null);
    }

    public Director saveDirector(String directorName) {
        Director director = new Director();
        director.setDirectorName(directorName);
        return directorRepository.save(director);
    }

    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }
}
