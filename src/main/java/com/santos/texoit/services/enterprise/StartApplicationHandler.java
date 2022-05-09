package com.santos.texoit.services.enterprise;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import com.santos.texoit.entities.Movie;
import com.santos.texoit.entities.Producer;
import com.santos.texoit.services.impl.MovieServiceImpl;
import com.santos.texoit.services.impl.ProducerServiceImpl;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

@Component
public class StartApplicationHandler {
    private final static String ARQUIVO_CSV = "classpath:movielist.csv";
    private final static String REGEX_CLEAN_PRODUCER = "\\W*(, | and )\\W*";

    @Autowired
    private MovieServiceImpl movieServiceImpl;

    @Autowired
    private ProducerServiceImpl producerServiceImpl;

    @PostConstruct
    public void loadDataIntoH2() {
        try {
            List<Record> recordList = readFile();
            readAndPersistProducers(recordList);
            readAndPersistMovies(recordList);
        } catch (Throwable e) {
            throw new RuntimeException("Ocorreu um erro ao ler o arquivo csv: " + e.getMessage());
        }
    }

    private void readAndPersistProducers(List<Record> recordList) {
        List<String> recordProducerList = new ArrayList<>();
        Set<String> producerUnique = new HashSet<>();
        recordList.forEach(record -> recordProducerList.add(record.getString("producers")));
        recordProducerList.forEach(item -> producerUnique.addAll(splitProducers(item)));
        producerUnique.forEach(item -> producerServiceImpl.save(Producer.builder().name(item).build()));
    }

    private Set<String> splitProducers(String item) {
        return Arrays.stream(item.split(REGEX_CLEAN_PRODUCER)).collect(Collectors.toSet());
    }

    private void readAndPersistMovies(List<Record> recordList) {
        List<Movie> movieList = new ArrayList<>();
        recordList.forEach(record -> {
            Movie.MovieBuilder builder = Movie.builder()
                    .year(record.getInt("year"))
                    .title(record.getString("title"))
                    .studios(record.getString("studios"))
                    .winner("yes".equals(record.getString("winner")));
            List<Producer> producers = producerServiceImpl.findByNameIn(splitProducers(record.getString("producers")));
            builder.producers(new HashSet<>(producers));
            movieList.add(builder.build());
        });
        movieServiceImpl.saveAll(movieList);
    }

    private List<Record> readFile() throws FileNotFoundException {
        File file = ResourceUtils.getFile(ARQUIVO_CSV);
        InputStream in = new FileInputStream(file);
        CsvParserSettings setting = new CsvParserSettings();
        setting.setHeaderExtractionEnabled(true);
        setting.setDelimiterDetectionEnabled(true);
        CsvParser csvParser = new CsvParser(setting);
        return csvParser.parseAllRecords(in);
    }
}
