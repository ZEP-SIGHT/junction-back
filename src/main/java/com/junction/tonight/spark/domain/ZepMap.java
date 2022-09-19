package com.junction.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "zep_map", catalog = "zep")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ZepMap {

    @Id
    @Column(name = "map_hash")
    private String mapHash;

    @Column
    private String mapName;

    @Column
    @ColumnDefault(value = "0")
    private Integer visitedCnt;
}
