package com.junction.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "time_log", catalog = "zep")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StayTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mapHash;

    @JoinColumn(name = "zep_map")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private ZepMap zepMap; // 기존 mapHash

    @Column
    private String areaName;

    @Column
    private String playerId;

    @Column
    private String playerAuth;

    @Column
    private LocalDateTime inTime;

    @Column
    private LocalDateTime outTime;

    @Column
    //@Comment("seconds 값만")
    private String stayTime;


}
