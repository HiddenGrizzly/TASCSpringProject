package com.example.moviemingle.services.writer;

import com.example.moviemingle.entities.Writer;
import com.example.moviemingle.repositories.WriterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WriterServiceImpl implements WriterService {
    @Autowired
    private WriterRepository writerRepository;

    @Override
    public Page<Writer> findAllWriters(Pageable pageable) {
        Page<Writer> writers = writerRepository.findAll(pageable);
        return writers;
    }

    @Override
    public Writer findWriterById(Long id) {
        return writerRepository.findById(id).orElse(null);
    }

    @Override
    public Writer saveWriter(String writerName) {
        Writer writer = new Writer();
        writer.setWriterName(writerName);
        return writerRepository.save(writer);
    }

    @Override
    public Writer updateWriter(Long id, String writerName) {
        Writer writer = findWriterById(id);
        if (writer == null) {
            return null;
        }
        writer.setWriterName(writerName);
        return writerRepository.save(writer);
    }

    @Override
    public void deleteWriter(Long id) {
        writerRepository.deleteById(id);
    }

    @Override
    public Writer findOrCreateWriter(String writerName) {
        Optional<Writer> writerOptional = writerRepository.findByWriterName(writerName);
        return writerOptional.orElseGet(() -> {
            Writer newWriter = new Writer();
            newWriter.setWriterName(writerName);
            return writerRepository.save(newWriter);
        });
    }
}
