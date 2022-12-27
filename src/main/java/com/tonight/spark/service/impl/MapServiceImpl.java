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
        String[] split = urlCollect.getMapUrl().split("play/");
        String mapHash = split[1];

        repository.findMapByMapHash(mapHash).ifPresent(a -> {
            throw new IllegalArgumentException("이미 존재하는 Zep Map 입니다.");
        });

        Map map = Map.builder()
                .mapName(mapName)
                .mapHash(mapHash)
                .build();
        repository.save(map);

        return mapHash;
    }

}
