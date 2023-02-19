package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class InfoService {

    Logger logger = LoggerFactory.getLogger(InfoService.class);

    @Value("${server.port}")
    private int port;

    public Integer getPort() {
        return port;
    }

    public Integer getNumber() {
        logger.debug("Создан метод получения числа");
        final int LIMIT = 1_000_000;
        List<Integer> test = new ArrayList<>();
        for (int i = 1; i < 1_000_001; i++) {
            test.add(i);
        }
        return Stream.of(test).
                reduce(0, Integer::sum);
    }

}
