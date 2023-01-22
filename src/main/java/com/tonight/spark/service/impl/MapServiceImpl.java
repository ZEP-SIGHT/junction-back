package com.tonight.spark.service.impl;

import com.tonight.spark.domain.Map;
import com.tonight.spark.dto.MapInfo;
import com.tonight.spark.repository.MapRepository;
import com.tonight.spark.service.MapService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MapServiceImpl implements MapService {

    private final MapRepository repository;

    public String saveUrlInfo(MapInfo urlCollect) {

        String mapName = urlCollect.getMapName();
        String[] split = urlCollect.getMapUrl().split("play/");
        String mapHash = split[1];

        Map findMap = repository.findMapByMapHash(mapHash);
        if (Objects.isNull(findMap)) {
            Map map = Map.builder()
                    .mapHash(mapHash)
                    .mapName(mapName)
                    .build();
            repository.save(map);
        }
        return mapHash;
    }

}
