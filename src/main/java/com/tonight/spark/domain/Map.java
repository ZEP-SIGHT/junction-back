package com.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity(name = "zep_map")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Map {

    @Id
    @Column(name = "map_hash")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String mapHash;

    @Column(name = "map_name")
    private String mapName;
}
