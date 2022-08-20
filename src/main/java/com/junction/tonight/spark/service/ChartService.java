package com.junction.tonight.spark.service;


import com.junction.tonight.spark.dto.ChartArea;

import java.util.List;

public interface ChartService {

    List<ChartArea> getChartsArea(String mapHash);


}
