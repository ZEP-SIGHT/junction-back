package com.junction.tonight.spark.dto.page1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class StatisForAuth {

    public HashMap<String, List> dataForAuth;


}

