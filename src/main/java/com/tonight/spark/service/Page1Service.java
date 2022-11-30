package com.tonight.spark.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tonight.spark.dto.PageOneDto;
import com.tonight.spark.dto.PageOneDtoRefactoring;

import java.util.List;

public interface Page1Service {
    List<PageOneDto> getDataForAuth(String mapHash) throws JsonProcessingException;
    List<PageOneDtoRefactoring> getDataForAuthRefactoring(String mapHash) throws JsonProcessingException;

    void test();
}
