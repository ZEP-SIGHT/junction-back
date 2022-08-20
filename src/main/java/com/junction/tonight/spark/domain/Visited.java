package com.junction.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Entity(name = "visitedTBL")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Visited {

    @Id
    @Column
    @GeneratedValue
    private String vId;

    @Column
    private String mapHash;

    @Column
    private String designatedAreaName;

    @Column
    private String vPlayerId;

    @Column
    private Integer vPlayerAuth;
}