package com.junction.tonight.spark.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.junction.tonight.spark.dto.page1.StatisForAuth;

public interface Page1Service {
    StatisForAuth getDataForAuth() throws JsonProcessingException;
}
