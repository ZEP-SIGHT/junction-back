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
@Entity(name = "mapTBL")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Map {

    @Id
    @Column
    @GeneratedValue
    private String mapHash;

    @Id
    private String mapName;
}
