package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        return Stream
                .iterate(1, a -> a +1)
                .limit(1_000_000)
                .parallel()
                .reduce(0, (a, b) -> a + b );
    }

}
