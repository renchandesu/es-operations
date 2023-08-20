package com.example.demoessearch.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ElasticServiceTest {

    @Autowired
    private ElasticService elasticService;

    @Test
    void insertData() {
        elasticService.insertData();

    }

    @Test
    void insertBatch() {
        elasticService.insertBatch();
    }
}