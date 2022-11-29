package com.tonight.spark.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class StayTimeDto {

    private String mapHash;

    private String areaName;

    private String playerId;

    private String playerAuth;

    private LocalDateTime inTime;

    private LocalDateTime outTime;

    @Setter
    private Long duration;

}
