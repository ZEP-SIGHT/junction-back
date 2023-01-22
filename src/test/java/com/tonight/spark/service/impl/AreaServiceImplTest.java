package com.tonight.spark.service.impl;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.domain.Visited;
import com.tonight.spark.dto.AreaData;
import com.tonight.spark.dto.NumberVisitor;
import com.tonight.spark.dto.page3.BounceDto;
import com.tonight.spark.dto.page3.RemainBounceDto;
import com.tonight.spark.dto.page3.RemainDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AreaServiceImplTest {

    static final String MAP_HASH = "mapHashSample";

    @Nested
    static class 방문자_수_테스트 {
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

    }

    @Nested
    static class 잔여_이탈률_테스트 {

        private static List<StayTime> dataSetUp() {
            return List.of(
                    new StayTime(1L, "123", "area1", "1", "MASTER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            20),
                    new StayTime(2L, "123", "area2", "2", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            10),
                    new StayTime(3L, "123", "area3", "3", "TEACHER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            20),
                    new StayTime(4L, "123", "area2", "1", "MASTER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            10),
                    new StayTime(5L, "123", "area4", "2", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            17),
                    new StayTime(6L, "123", "area2", "3", "TEACHER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            40),
                    new StayTime(7L, "123", "area3", "2", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            20),
                    new StayTime(8L, "123", "area1", "1", "MASTER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            20),
                    new StayTime(9L, "123", "area2", "4", "TEACHER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            20),
                    new StayTime(10L, "123", "area3", "6", "TEACHER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            30),
                    new StayTime(11L, "123", "area1", "1", "MASTER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            20),
                    new StayTime(12L, "123", "area1", "10", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            18),
                    new StayTime(13L, "123", "area1", "11", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            14),
                    new StayTime(15L, "123", "area1", "11", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            1),
                    new StayTime(16L, "123", "area2", "11", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            4),
                    new StayTime(13L, "123", "area3", "11", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            2),
                    new StayTime(13L, "123", "area4", "11", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            3),
                    new StayTime(14L, "123", "area1", "11", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            4),
                    new StayTime(15L, "123", "area1", "11", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            3),
                    new StayTime(16L, "123", "area2", "11", "MEMBER",
                            LocalDateTime.parse("2021-11-08T11:44:30.327959"),
                            LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                            4)
            );
        }

        @Test
        void 잔여율_테스트() {
            List<StayTime> stayTimes = dataSetUp(); // mapHash Query 친 것
            Set<String> areaSet = stayTimes.stream().map(StayTime::getAreaName).collect(Collectors.toSet());
            Map<String, Integer> remainTimes = stayTimes.stream()
                    .collect(groupingBy(StayTime::getAreaName, summingInt(StayTime::getDuration)));
            System.out.println("remainTimes = " + remainTimes);

            List<RemainDto> remainingTimes = remainTimes.entrySet().stream()
                    .map(RemainDto::create).collect(toList());

            // remainingTimes 로 부터 totalNum 구한다.
            // getDuration -> 5s 이하인것 필터링
            Map<String, Integer> bounceTimes = stayTimes.stream()
                    .collect(groupingBy(StayTime::getAreaName,
                            filtering(data -> data.getDuration() <= 5, summingInt(StayTime::getDuration)))
                    );
            System.out.println("bounceTimes = " + bounceTimes);
            DecimalFormat form = new DecimalFormat("#.#");
            List<BounceDto> collect = bounceTimes.entrySet()
                    .stream()
                    .map(data -> BounceDto.builder()
                            .areaName(data.getKey())
                            .accumulateTime(form.format((double) data.getValue() / remainTimes.get(data.getKey()) * 100))
                            .build()
                    )
                    .collect(toList());

            // remains 에는 존재하는 key 가  bounce에는 없을 위험있다.
            Integer totalRemainTimes = remainTimes.values().stream().reduce(0, Integer::sum);
            Integer totalBounceTimes = bounceTimes.values().stream().reduce(0, Integer::sum);
            RemainBounceDto build = RemainBounceDto.builder()
                    .totalRemainTime(totalRemainTimes)
                    .remainDto(remainingTimes)
                    .totalBounceTimes(form.format((double) totalBounceTimes / totalRemainTimes * 100))
                    .bounceDto(collect)
                    .build();
        }

    }
    // map 을 재활용한다고 치자. (day1, day2)
    // day1 의 totalCount 가, day2 의 totalCount 와 누적되면 안되나?
}