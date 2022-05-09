package com.santos.texoit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.santos.texoit.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
