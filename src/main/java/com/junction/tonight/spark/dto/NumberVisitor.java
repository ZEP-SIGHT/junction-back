package com.junction.tonight.spark.dto;


import com.junction.tonight.spark.domain.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NumberVisitor {

    public Integer totalNumber;

    public HashMap<String, Integer> areaData;


}
