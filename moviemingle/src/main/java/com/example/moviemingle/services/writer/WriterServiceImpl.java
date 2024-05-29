package com.example.moviemingle.services.writer;

import com.example.moviemingle.entities.Genre;
import com.example.moviemingle.entities.Writer;
import com.example.moviemingle.repositories.GenreRepository;
import com.example.moviemingle.repositories.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class WriterServiceImpl implements WriterService {
    @Autowired
    private WriterRepository writerRepository;

    public Page<Writer> findAllWriters(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return writerRepository.findAll(pageable);
    }

    public Writer findWriterById(Long id) {
        return writerRepository.findById(id).orElse(null);
    }

    public Writer saveWriter(String writerName) {
        Writer writer = new Writer();
        writer.setWriterName(writerName);
        return writerRepository.save(writer);
    }

    public void deleteWriter(Long id) {
        writerRepository.deleteById(id);
    }
}
