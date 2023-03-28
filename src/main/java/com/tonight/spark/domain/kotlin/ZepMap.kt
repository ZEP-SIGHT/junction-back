package com.tonight.spark.domain.kotlin

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "zep_map")
data class ZepMap(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Column(name = "map_hash")
        var mapHash: String? = null,

        @Column(name = "map_name")
        var mapName: String? = null
){

}