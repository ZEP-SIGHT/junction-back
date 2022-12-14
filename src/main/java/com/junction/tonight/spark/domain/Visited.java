package com.junction.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Entity(name = "visitedTBL")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visited {

    @Id
    @Column
    @GeneratedValue
    private Long vId;

    @Column
    private String mapHash;

    @Column
    private String designatedAreaName;

    @Column
    private String vPlayerId;

    @Column
    private String vPlayerAuth;
}