package com.junction.tonight.spark.service.impl;

import com.junction.tonight.spark.domain.ZepMap;
import com.junction.tonight.spark.dto.MapInfo;
import com.junction.tonight.spark.repository.MapRepository;
import com.junction.tonight.spark.service.MapService;
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

        ZepMap zepMap = new ZepMap(mapHash, mapName);
        repository.save(zepMap);

//        EnterResponse.builder()..build();
        return mapHash;
    }

}
