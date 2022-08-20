package com.junction.tonight.spark.service;


import com.junction.tonight.spark.dto.ChartArea;
import com.junction.tonight.spark.dto.TimeCount;

import java.util.List;

public interface ChartService {

    List<TimeCount> getTotalVisits(String mapHash);

    List<ChartArea> getChartsArea(String mapHash);


}
