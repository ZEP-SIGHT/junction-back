package com.tonight.spark.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tonight.spark.dto.PageOneDto;

import java.util.List;

public interface Page1Service {
    List<PageOneDto> getDataForAuth(String mapHash) throws JsonProcessingException;

    void test();
}
