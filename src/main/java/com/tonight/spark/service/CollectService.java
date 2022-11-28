package com.tonight.spark.service;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;
import com.tonight.spark.dto.VisitDto;


public interface CollectService {

    // 방문 시
    Visited visitArea(VisitDto visited);

    // 떠날 때
    StayTime leaveArea(StayTime stayTime);

}
