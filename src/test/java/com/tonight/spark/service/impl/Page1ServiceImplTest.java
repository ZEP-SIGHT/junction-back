package com.tonight.spark.service.impl;

import com.tonight.spark.domain.StayTime;
import com.tonight.spark.dto.*;
import com.tonight.spark.repository.StayTimeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.*;

class Page1ServiceImplTest {

    @Autowired
    StayTimeRepository stayTimeRepository;

    List<StayTime> stayTimeByMapHash = new ArrayList<>();

    private static List<StayTime> dataSetUp() {
        return List.of(
                new StayTime(1L, "123", "area1", "1", "MASTER",
                        LocalDateTime.parse("2021-11-08T01:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                        20),
                new StayTime(2L, "123", "area2", "2", "MEMBER",
                        LocalDateTime.parse("2021-11-08T14:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T16:44:35.327959"),
                        10),
                new StayTime(3L, "123", "area3", "3", "TEACHER",
                        LocalDateTime.parse("2021-11-08T15:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T19:44:35.327959"),
                        20),
                new StayTime(4L, "123", "area2", "1", "MASTER",
                        LocalDateTime.parse("2021-11-08T04:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T10:44:35.327959"),
                        10),
                new StayTime(5L, "123", "area4", "2", "MEMBER",
                        LocalDateTime.parse("2021-11-08T02:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T09:44:35.327959"),
                        20),
                new StayTime(6L, "123", "area2", "3", "TEACHER",
                        LocalDateTime.parse("2021-11-08T06:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T09:44:35.327959"),
                        40),
                new StayTime(7L, "123", "area3", "2", "MEMBER",
                        LocalDateTime.parse("2021-11-08T02:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T10:44:35.327959"),
                        20),
                new StayTime(8L, "123", "area1", "1", "MASTER",
                        LocalDateTime.parse("2021-11-08T12:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T13:44:35.327959"),
                        20),
                new StayTime(9L, "123", "area2", "4", "TEACHER",
                        LocalDateTime.parse("2021-11-08T09:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                        20),
                new StayTime(10L, "123", "area3", "6", "TEACHER",
                        LocalDateTime.parse("2021-11-08T01:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T11:44:35.327959"),
                        30),
                new StayTime(11L, "123", "area1", "1", "MASTER",
                        LocalDateTime.parse("2021-11-08T05:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T07:44:35.327959"),
                        20),
                new StayTime(12L, "123", "area1", "10", "MEMBER",
                        LocalDateTime.parse("2021-11-08T14:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T18:44:35.327959"),
                        15),
                new StayTime(13L, "123", "area1", "11", "MEMBER",
                        LocalDateTime.parse("2021-11-08T02:44:30.327959"),
                        LocalDateTime.parse("2021-11-08T05:44:35.327959"),
                        14)
        );
    }

    @Test
    public void Page1_리팩토링() {
        List<PageOneDtoOLD> finalResult = new ArrayList<>();
        List<StayTime> stayTimes = dataSetUp(); // mapHash Query 친 것
        Map<String, Map<String, List<Integer>>> nearResult = stayTimes.stream().collect(
                groupingBy(StayTime::getPlayerAuth,
                        groupingBy(StayTime::getAreaName, mapping(StayTime::getDuration, toList()))
                )
        );
        Map<String, Map<String, TimeCount>> result = new HashMap<>();
        for (String auth : nearResult.keySet()) {
            Map<String, List<Integer>> byAuth = nearResult.get(auth);
            Map<String, TimeCount> areaMap = new HashMap<>();
            ArrayList<AreaTimeCount> areaList = new ArrayList<>();
            for (String area : byAuth.keySet()) {
                List<Integer> durations = byAuth.get(area);
                areaMap.put(area, new TimeCount(durations.stream().reduce(0, Integer::sum),
                        durations.size()));
            }
        }
        System.out.println("PageOneDto");
        for (String s : result.keySet()) {
            System.out.println(s);
            Map<String, TimeCount> timeCountMap = result.get(s);
            for (String s1 : timeCountMap.keySet()) {
                System.out.println("------> " + s1);
                TimeCount timeCount = timeCountMap.get(s1);
                System.out.println("------> " + timeCount.toString());
            }
        }
    }

    @Test
    void Page1_리팩토링_Dto_수정() {
        List<StayTime> stayTimes = dataSetUp(); // mapHash Query 친 것
        Map<String, Map<String, List<Integer>>> nearResult = stayTimes.stream().collect(
                groupingBy(StayTime::getPlayerAuth,
                        groupingBy(StayTime::getAreaName, mapping(StayTime::getDuration, toList()))
                )
        );


// try 1
//        for (String auth : nearResult.keySet()) {
//            Map<String, List<String>> byAuth = nearResult.get(auth);
//            ArrayList<AreaTimeCount> areaList = new ArrayList<>();
//            for (String area : byAuth.keySet()) {
//                List<String> durations = byAuth.get(area);
//                AreaTimeCount areaT = AreaTimeCount.builder()
//                        .areaName(area)
//                        .time(durations.stream().mapToInt(Integer::parseInt).sum())
//                        .count(durations.size())
//                        .build();
//                areaList.add(areaT);
//            }
//            PageOneDtoRefactoring build = PageOneDtoRefactoring.builder()
//                    .authority(auth)
//                    .areas(areaList)
//                    .build();
//            finalResult.add(build);
//        }

        // try 2
//        for (Map.Entry<String, Map<String, List<String>>> entry : nearResult.entrySet()) {
//            finalResult.add(
//                    PageOneDtoRefactoring.create(entry.getKey(), getAreaTimeCounts(entry.getValue()))
//            );
//        }

        // try3
        List<PageOneDto> finalResult = nearResult.entrySet().stream()
                .map(data -> PageOneDto.create(data.getKey(), getAreaTimeCounts(data.getValue())))
                .collect(toList());

        for (PageOneDto pageOneDto : finalResult) {
            System.out.println(pageOneDto.authority);
            System.out.println(pageOneDto.areas.toString());
        }
    }

    private static List<AreaTimeCount> getAreaTimeCounts(Map<String, List<Integer>> byAuth) {
        return byAuth.entrySet()
                .stream()
                .map(AreaTimeCount::create)
                .collect(toList());
//        return byAuth.keySet()
//                .stream()
//                .map(area -> AreaTimeCount.create(area, byAuth.get(area)))
//                .collect(toList());
    }

    @Test
    public void ChartArea_Logic_Test() {
        List<StayTime> stayTimes = dataSetUp(); // mapHash Query 친 것

        Map<String, Map<Integer, Long>> result = stayTimes.stream()
                .collect(groupingBy(StayTime::getAreaName,
                        groupingBy(data -> data.getInTime().getHour(), counting())));

        List<ChartArea> finalResult = result.entrySet()
                .stream()
                .map(ChartArea::new)
                .collect(toList());
        for (ChartArea chartArea : finalResult) {
            System.out.println(chartArea.getAreaName());
            System.out.println(chartArea.getTimeCountList());
        }
    }
}