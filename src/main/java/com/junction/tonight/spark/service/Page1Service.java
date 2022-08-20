package com.junction.tonight.spark.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.junction.tonight.spark.dto.PageOneDto;
import com.junction.tonight.spark.dto.TimeCount;
import com.junction.tonight.spark.dto.page1.StatisForAuth;

import java.util.HashMap;
import java.util.List;

public interface Page1Service {
    List<PageOneDto> getDataForAuth(String mapHash) throws JsonProcessingException;

    void test();
}
