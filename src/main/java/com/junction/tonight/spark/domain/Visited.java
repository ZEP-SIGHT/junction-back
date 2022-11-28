package com.junction.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity(name = "visit_log")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visited {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "map_hash")
    private String mapHash;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "player_id")
    private String playerId;

    @Column(name = "player_auth")
    private String playerAuth;
}