package com.tonight.spark.service.impl;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;
import com.tonight.spark.repository.StayTimeRepository;
import com.tonight.spark.repository.VisitedRepository;
import com.tonight.spark.service.CollectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class CollectServiceImpl implements CollectService {

    private final VisitedRepository visitedRepository;

    private final StayTimeRepository stayTimeRepository;

    private final String COLON = ":";

    @Override
    public Visited visitArea(Visited visited) {

        return visitedRepository.save(visited);
    }

    @Override
    public StayTime leaveArea(StayTime stayTime) {
        stayTime.setStayTime(String.valueOf(ChronoUnit.SECONDS.between(stayTime.getInTime(), stayTime.getOutTime())));

        return stayTimeRepository.save(stayTime);
    }
}

