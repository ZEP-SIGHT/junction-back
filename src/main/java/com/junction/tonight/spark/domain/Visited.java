package com.junction.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity
@Table(name = "visit_logs", catalog = "zep")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visited {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @GeneratedValue
    private Long Id;

    @JoinColumn(name = "zep_map")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private ZepMap zepMap;

    @Column
    private String areaName;

    @Column
    private String playerId;

    @JoinColumn(name="layer_auth")
    @ManyToOne
    private UserAuth userAuth;
}