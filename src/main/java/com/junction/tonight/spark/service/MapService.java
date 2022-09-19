package com.junction.tonight.spark.service;

import com.junction.tonight.spark.domain.Map;
import com.junction.tonight.spark.dto.MapInfo;

public interface MapService {
    String saveUrlInfo(MapInfo urlCollect);

    Map getConcurrentCnt(String mapHash);

    Map saveConCurrentCnt(String mapHash, int conCurCnt);
}
