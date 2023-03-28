package com.tonight.spark.service.kotlin

import com.tonight.spark.dto.kotlin.MapInfo

interface MapService {
    fun saveUrlInfo(mapInfo: MapInfo): String

}