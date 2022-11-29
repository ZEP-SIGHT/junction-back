package com.tonight.spark.service;

import com.tonight.spark.dto.BaseDataFormat;
import com.tonight.spark.dto.NumberVisitor;

import java.util.HashMap;

public interface AreaService {

    public NumberVisitor getNumberOfVisitor(String mapHash);

    public HashMap<String, BaseDataFormat> getRemainTime(String mapHash);

}
