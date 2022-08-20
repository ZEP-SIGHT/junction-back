package com.junction.tonight.spark.service.impl;

import com.junction.tonight.spark.domain.StayTime;
import com.junction.tonight.spark.domain.Visited;
import com.junction.tonight.spark.repository.StayTimeRepository;
import com.junction.tonight.spark.repository.VisitedRepository;
import com.junction.tonight.spark.service.CollectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CollectServiceImpl implements CollectService{

    private final VisitedRepository visitedRepository;

    private final StayTimeRepository stayTimeRepository;

    @Override
    public Visited visitArea(Visited visited) {

        return visitedRepository.save(visited);
    }

    @Override
    public StayTime leaveArea(StayTime stayTime) {

        return stayTimeRepository.save(stayTime);
    }
}
