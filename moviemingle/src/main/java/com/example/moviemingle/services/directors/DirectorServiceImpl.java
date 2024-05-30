package com.example.moviemingle.services.directors;

import com.example.moviemingle.entities.Director;
import com.example.moviemingle.repositories.DirectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DirectorServiceImpl implements DirectorService {
    @Autowired
    private DirectorRepository directorRepository;

    @Override
    public Page<Director> findAllDirectors(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return directorRepository.findAll(pageable);
    }

    @Override
    public Director findDirectorById(Long id) {
        return directorRepository.findById(id).orElse(null);
    }

    @Override
    public Director saveDirector(String directorName) {
        Director director = new Director();
        director.setDirectorName(directorName);
        return directorRepository.save(director);
    }

    @Override
    public Director updateDirector(Long id, String directorName) {
        Director director = findDirectorById(id);
        if (director == null) {
            return null;
        }
        director.setDirectorName(directorName);
        return directorRepository.save(director);
    }

    @Override
    public void deleteDirector(Long id) {
        directorRepository.deleteById(id);
    }

    @Override
    public Director findOrCreateDirector(String directorName) {
        Optional<Director> directorOptional = directorRepository.findByDirectorName(directorName);
        return directorOptional.orElseGet(() -> {
            Director newDirector = new Director();
            newDirector.setDirectorName(directorName);
            return directorRepository.save(newDirector);
        });
    }
}
