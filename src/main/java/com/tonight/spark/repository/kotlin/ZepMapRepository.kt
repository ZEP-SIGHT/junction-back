package com.tonight.spark.repository.kotlin

import com.tonight.spark.domain.kotlin.ZepMap
import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface ZepMapRepository : JpaRepository<ZepMap, Long> {
    fun findByMapHash(hash: String): Optional<ZepMap>
}