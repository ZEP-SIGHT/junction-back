package com.tonight.spark.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageOneDtoOLD {
    public String authorityName;
    public HashMap<String, List<TimeCount>> mapData;
}
