package com.junction.tonight.spark.service;

import com.junction.tonight.spark.dto.MapHashResponse;
import com.junction.tonight.spark.dto.MapInfo;

public interface MapService {

    MapHashResponse saveUrlInfo(MapInfo urlCollect);

}
