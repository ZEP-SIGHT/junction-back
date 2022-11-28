package com.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "zep_map")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Map {

    @Id
    @Column(name = "map_hash")
    private String mapHash;

    @Column(name = "map_name")
    private String mapName;
}
