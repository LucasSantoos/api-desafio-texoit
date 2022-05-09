package com.santos.texoit.repositories;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.santos.texoit.entities.Producer;

public interface ProducerRepository extends JpaRepository<Producer, Long> {

    List<Producer> findByNameIn(Collection<String> names);

}
