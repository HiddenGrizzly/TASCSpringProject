package com.example.moviemingle.entities;

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
@Table(name = "directors")
public class Director extends BaseEntity {

    private String directorName;

    @ManyToMany(mappedBy = "directors")
    private Set<Movie> movies = new HashSet<>();

}