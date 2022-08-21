package com.junction.tonight.spark.service;


import com.junction.tonight.spark.dto.ChartArea;
import com.junction.tonight.spark.dto.TotalVisit;
import java.util.List;

public interface ChartService {

    TotalVisit getTotalVisits(String mapHash);

    List<ChartArea> getChartsArea(String mapHash);


}
