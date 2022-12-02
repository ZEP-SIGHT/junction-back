package com.tonight.spark.service.impl;

import com.tonight.spark.domain.Visited;
import com.tonight.spark.dto.AreaData;
import com.tonight.spark.dto.NumberVisitor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AreaServiceImplTest {

    static final String MAP_HASH = "mapHashSample";

    static final String AREA_1 = "area1";
    public static final String AREA_2 = "area2";
    public static final String AREA_3 = "area3";

    static final Integer AREA1COUNT = 8;
    static final Integer AREA2COUNT = 7;
    static final Integer AREA3COUNT = 6;

    static List<Visited> resultByHash;
    static NumberVisitor build;
    static Map<String, List<Visited>> areaMap;
    static Integer totalCount;
    static List<AreaData> areaData;


    private static List<Visited> dataSetUp() {

        // areaName 과 playerId 로 복합키 쳐서, 중복 방문 필터링 해야할것 같은데. 
        return List.of(
                new Visited(1L, MAP_HASH, AREA_1, "1", "Master"),
                new Visited(1L, MAP_HASH, AREA_1, "2", "Master"),
                new Visited(1L, MAP_HASH, AREA_1, "3", "Master"),
                new Visited(1L, MAP_HASH, AREA_1, "4", "Master"),
                new Visited(1L, MAP_HASH, AREA_1, "5", "Master"),
                new Visited(1L, MAP_HASH, AREA_1, "6", "Master"),
                new Visited(1L, MAP_HASH, AREA_1, "7", "Master"),
                new Visited(1L, MAP_HASH, AREA_1, "8", "Master"),


                new Visited(1L, MAP_HASH, AREA_2, "11", "Master"),
                new Visited(1L, MAP_HASH, AREA_2, "21", "Master"),
                new Visited(1L, MAP_HASH, AREA_2, "31", "Master"),
                new Visited(1L, MAP_HASH, AREA_2, "41", "Master"),
                new Visited(1L, MAP_HASH, AREA_2, "51", "Master"),
                new Visited(1L, MAP_HASH, AREA_2, "71", "Master"),
                new Visited(1L, MAP_HASH, AREA_2, "81", "Master"),

                new Visited(1L, MAP_HASH, AREA_3, "21", "Master"),
                new Visited(1L, MAP_HASH, AREA_3, "22", "Master"),
                new Visited(1L, MAP_HASH, AREA_3, "25", "Master"),
                new Visited(1L, MAP_HASH, AREA_3, "26", "Master"),
                new Visited(1L, MAP_HASH, AREA_3, "27", "Master"),
                new Visited(1L, MAP_HASH, AREA_3, "28", "Master")
        );
    }


    @BeforeAll
    static void before_Test() {
        resultByHash = dataSetUp().stream()
                .filter(data -> data.getMapHash().equals(MAP_HASH))
                .collect(Collectors.toList());

        areaMap = resultByHash.stream().collect(groupingBy(Visited::getAreaName));

        totalCount = resultByHash.size();

        areaData = areaMap.entrySet()
                .stream()
                .map(AreaData::create)
                .collect(Collectors.toList());

        build = NumberVisitor.builder()
                .totalCount(totalCount)
                .areaData(areaData)
                .build();

    }

    @Test
    void AREA__총_개수_테스트() {
        assertEquals(build.areaData.size(), areaMap.keySet().size());
    }

    @Test
    void AREA별_방문수_테스트() {

        int area1Count = build.areaData.stream()
                .filter(d -> d.getAreaName().equals(AREA_1))
                .mapToInt(AreaData::getAreaCount)
                .sum();

        assertEquals(AREA1COUNT, area1Count);
        assertEquals(AREA1COUNT, areaMap.get(AREA_1).size());
        assertEquals(area1Count, areaMap.get(AREA_1).size());
        assertEquals(AREA2COUNT, areaMap.get(AREA_2).size());
        assertEquals(AREA3COUNT, areaMap.get(AREA_3).size());
    }

    // map 을 재활용한다고 치자. (day1, day2)
    // day1 의 totalCount 가, day2 의 totalCount 와 누적되면 안되나?
}