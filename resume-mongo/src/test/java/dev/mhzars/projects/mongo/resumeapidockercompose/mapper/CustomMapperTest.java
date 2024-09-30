package dev.mhzars.projects.mongo.resumeapidockercompose.mapper;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Slf4j
class CustomMapperTest {
    private CustomMapper mapper;

    @BeforeEach
    void init() {
        mapper = new CustomMapper();
        mapper.configure(new DefaultMapperFactory.Builder().build());
    }

    @Test
    void testUUIDtoString() {
        UUID newId = mapper.map(UUID.randomUUID().toString(), UUID.class);
        log.info("{}", newId);
        assertNotNull(newId);
    }

    @Test
    void testStringtoUUID() {
        String newId = mapper.map(UUID.randomUUID(), String.class);
        log.info("{}", newId);
        assertNotNull(newId);
    }
}