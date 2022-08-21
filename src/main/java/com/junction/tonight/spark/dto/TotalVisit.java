package com.junction.tonight.spark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotalVisit {
    public int totalVisit;
    public List<TimeCountDto> timeCount;



}
