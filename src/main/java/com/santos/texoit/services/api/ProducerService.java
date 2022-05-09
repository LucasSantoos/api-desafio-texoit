package com.santos.texoit.services.api;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import javax.xml.bind.ValidationException;

import com.santos.texoit.entities.Producer;
import com.santos.texoit.representations.WinnerInterval;

public interface ProducerService {

    List<Producer> findAll();

    Producer findById(Long id) throws ValidationException;

    List<Producer> getWinners();

    Set<WinnerInterval> getWinnersInterval();

    List<Producer> findByNameIn(Collection<String> names);

    Producer save(Producer producer);

    void removeById(Long id) throws ValidationException;
}
