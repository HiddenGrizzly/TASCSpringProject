package com.example.moviemingle.repositories;

import com.example.moviemingle.entities.Writer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface WriterRepository extends JpaRepository<Writer, Long>, JpaSpecificationExecutor<Writer> {
}
