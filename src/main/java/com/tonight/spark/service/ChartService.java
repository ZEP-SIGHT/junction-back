package com.tonight.spark.service;


import com.tonight.spark.dto.ChartArea;
import com.tonight.spark.dto.TotalVisit;
import java.util.List;

public interface ChartService {

    TotalVisit getTotalVisits(String mapHash);

    List<ChartArea> getChartsArea(String mapHash);


}
