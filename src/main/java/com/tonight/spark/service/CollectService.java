package com.tonight.spark.service;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;


public interface CollectService {

    // 방문 시
    Visited visitArea(Visited visited);

    // 떠날 때
    StayTime leaveArea(StayTime stayTime);

}
