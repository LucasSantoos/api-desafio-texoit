package com.santos.texoit.services.api;

import java.util.List;

import com.santos.texoit.entities.Movie;

public interface MovieService {

    List<Movie> findAll();

    List<Movie> saveAll(List<Movie> movieList);

    Movie findById(Long id);

    Movie save(Movie movie);

    void removeById(Long id);
}
