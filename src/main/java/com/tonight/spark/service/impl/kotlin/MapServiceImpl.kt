package com.tonight.spark.service.impl.kotlin

import com.tonight.spark.domain.kotlin.ZepMap
import com.tonight.spark.dto.kotlin.MapInfo
import com.tonight.spark.repository.kotlin.ZepMapRepository
import com.tonight.spark.service.kotlin.MapService
import org.springframework.stereotype.Service

@Service
class MapServiceImpl(private val repository: ZepMapRepository) : MapService {

    private val splitter = "/play"

    override fun saveUrlInfo(mapInfo: MapInfo): String {
        val mapName: String = mapInfo.mapName;
        val mapHash: String = mapInfo.mapUrl.split(splitter)[1]
        saveMapHashIfNotExist(mapName, mapHash)
        return mapHash
    }

    private fun saveMapHashIfNotExist(mapName: String, mapHash: String) {
        repository.findByMapHash(mapHash)
                .orElseGet { repository.save(ZepMap(mapName = mapName, mapHash = mapHash)) }
    }
}