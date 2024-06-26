package com.example.moviemingle.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "writers")
public class Writer extends BaseEntity {
    
    private String writerName;

    @ManyToMany(mappedBy = "writers")
    @JsonIgnore
    private Set<Movie> movies = new HashSet<>();
    
}