package com.example.moviemingle.services.writer;

import com.example.moviemingle.entities.Writer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface WriterService {
    Page<Writer> findAllWriters(Pageable pageable);

    Writer findWriterById(Long id);

    Writer saveWriter(String writerName);

    Writer updateWriter(Long id, String writerName);

    void deleteWriter(Long id);

    Writer findOrCreateWriter(String writerName);
}
