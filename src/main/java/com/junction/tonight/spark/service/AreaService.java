package com.junction.tonight.spark.service;

import com.junction.tonight.spark.dto.BaseDataFormat;
import com.junction.tonight.spark.dto.NumberVisitor;

import java.util.HashMap;

public interface AreaService {

    public NumberVisitor getNumberOfVisitor(String mapHash);

    public HashMap<String, BaseDataFormat> getRemainTime(String mapHash);

}
