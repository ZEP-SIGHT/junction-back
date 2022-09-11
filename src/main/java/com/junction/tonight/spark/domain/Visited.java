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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mapHash;

    @JoinColumn(name = "zep_map")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private ZepMap zepMap;


    @Column
    private String areaName;

    @Column
    private String playerId;

    @Column
    private String playerAuth;
}