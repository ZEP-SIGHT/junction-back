package com.tonight.spark.service.impl;

import com.tonight.spark.domain.Map;
import com.tonight.spark.dto.MapInfo;
import com.tonight.spark.repository.MapRepository;
import com.tonight.spark.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    private final MapRepository repository;

    public String saveUrlInfo(MapInfo urlCollect) {

        String mapName = urlCollect.getMapName();

        // url 에서 hash 값 추출
        String[] split = urlCollect.getMapUrl().split("play/");
        String mapHash = split[1];

        Map map = new Map(mapHash, mapName);
        repository.save(map);

//        EnterResponse.builder()..build();
        return mapHash;
    }

}
