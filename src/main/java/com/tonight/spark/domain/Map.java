package com.tonight.spark.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity(name = "zep_map")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Map {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "map_hash")
    private String mapHash;

    @Column(name = "map_name")
    private String mapName;
}
