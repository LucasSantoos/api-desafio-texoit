package com.santos.texoit.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santos.texoit.entities.Movie;
import com.santos.texoit.repositories.MovieRepository;
import com.santos.texoit.services.api.MovieService;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> saveAll(List<Movie> movieList) {
        return movieRepository.saveAll(movieList);
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Não foi possível localizar o filme com o código: " + id));
    }

    @Override
    public Movie save(Movie producer) {
        return movieRepository.save(producer);
    }

    @Override
    public void removeById(Long id) {
        movieRepository.delete(findById(id));
    }

}
