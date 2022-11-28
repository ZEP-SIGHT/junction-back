package com.tonight.spark.service.impl;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;
import com.tonight.spark.dto.StayTimeDto;
import com.tonight.spark.dto.VisitDto;
import com.tonight.spark.mapper.StayTimeMapper;
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
    public Visited visitArea(VisitDto visited) {

        Visited build = Visited.builder()
                .mapHash(visited.getMapHash())
                .areaName(visited.getAreaName())
                .playerId(visited.getPlayerId())
                .playerAuth(visited.getPlayerAuth())
                .build();

        return visitedRepository.save(build);
    }

    @Override
    public StayTime leaveArea(StayTimeDto dto) {
        long duration = ChronoUnit.SECONDS.between(dto.getInTime(), dto.getOutTime());
        dto.setDuration(duration);
        StayTime stayTime = StayTimeMapper.INSTANCE.dtoToStayTime(dto);

        return stayTimeRepository.save(stayTime);
    }
}

