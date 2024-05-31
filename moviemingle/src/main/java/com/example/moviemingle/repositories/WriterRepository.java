package com.example.moviemingle.repositories;

import com.example.moviemingle.entities.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long>, JpaSpecificationExecutor<Writer> {

    Optional<Writer> findByWriterName(String writerName);
}
