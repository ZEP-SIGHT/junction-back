package com.junction.tonight.spark.service;

import com.junction.tonight.spark.domain.StayTime;
import com.junction.tonight.spark.domain.Visited;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CollectService {

    // 방문 시
    Visited visitArea(Visited visited);

    // 떠날 때
    StayTime leaveArea(StayTime stayTime);



}
