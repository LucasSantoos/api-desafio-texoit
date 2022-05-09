package com.santos.texoit.services.impl;

import java.util.*;
import java.util.stream.Collectors;
import javax.xml.bind.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.santos.texoit.entities.Movie;
import com.santos.texoit.entities.Producer;
import com.santos.texoit.repositories.ProducerRepository;
import com.santos.texoit.representations.WinnerInterval;
import com.santos.texoit.services.api.ProducerService;

@Service
public class ProducerServiceImpl implements ProducerService {

    @Autowired
    private ProducerRepository producerRepository;

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }

    @Override
    public Producer findById(Long id) throws ValidationException {
        Producer producer = producerRepository.findById(id).orElse(null);
        if (producer != null) {
            return producer;
        }
        throw new ValidationException("Não foi possível localizar o produtor com o código: " + id);
    }

    @Override
    public List<Producer> findByNameIn(Collection<String> names) {
        return producerRepository.findByNameIn(names);
    }

    @Override
    public Producer save(Producer producer) {
        return producerRepository.save(producer);
    }

    @Override
    public void removeById(Long id) throws ValidationException {
        producerRepository.delete(findById(id));
    }

    @Override
    public List<Producer> getWinners() {
        return findAll()
                .stream()
                .filter(p -> p.getMovies() != null && p.getMovies().stream().anyMatch(Movie::isWinner))
                .collect(Collectors.toList());
    }

    @Override
    public Set<WinnerInterval> getWinnersInterval() {
        Set<WinnerInterval> winnerInterval = new HashSet<>();
        getWinners()
                .forEach(producer -> winnerInterval.addAll(generateWinnerIntervals(producer)));
        return winnerInterval;
    }

    private Set<WinnerInterval> generateWinnerIntervals(Producer producer) {
        Set<WinnerInterval> winnerIntervalList = new HashSet<>();
        Set<Movie> wins = producer.getMovies().stream().filter(Movie::isWinner).collect(Collectors.toSet());
        if (wins.size() < 2) {
            return winnerIntervalList;
        }

        Iterator<Movie> iterator = wins.stream()
                .sorted(Movie::compareTo)
                .iterator();

        Movie previousMovie = null;
        while (iterator.hasNext()) {
            Movie current = iterator.next();
            if (previousMovie != null && !previousMovie.equals(current)) {
                WinnerInterval winnerInterval = generateIntervalos(producer, previousMovie, current);
                winnerIntervalList.add(winnerInterval);
            }
            previousMovie = current;
        }

        return winnerIntervalList;
    }

    private WinnerInterval generateIntervalos(Producer producer, Movie previousMovie, Movie followingMovie) {
        return WinnerInterval
                .builder()
                .producer(producer.getName())
                .interval(followingMovie.getYear() - previousMovie.getYear())
                .previousWin(previousMovie.getYear())
                .followingWin(followingMovie.getYear())
                .build();
    }
}
