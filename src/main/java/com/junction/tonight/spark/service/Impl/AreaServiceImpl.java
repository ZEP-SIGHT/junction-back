package com.junction.tonight.spark.service.impl;

import com.junction.tonight.spark.domain.Map;
import com.junction.tonight.spark.domain.Visited;
import com.junction.tonight.spark.repository.VisitedRepository;
import com.junction.tonight.spark.service.AreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    // REPO 의존 주입
    private final VisitedRepository repository;


    @Override
    public Object getNumberOfVisitor() {

        Map mapHash = new Map(); // 어디선가 받아와야 한다.
        List<Visited> visitedByMap = repository.findVisitedByMap(mapHash);
        // 영역별 visitors
//        visitedByMap.stream()
//                .map(visited -> visited.getDesignatedAreaName())// 이름 별로 map 에 저장

        // sql 로 areaName -> 방문 playeId 를 다 섬 or distinct 하게 sum

        // map hash key 조회

        return null;
    }
}
