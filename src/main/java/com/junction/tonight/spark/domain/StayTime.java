package com.junction.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "stay_time_log")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StayTime {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "map_hash")
    private String mapHash;

    @Column(name = "area_name")
    private String areaName;

    @Column(name = "player_id")
    private String playerId;

    @Column(name = "player_auth")
    private String playerAuth;

    @Column(name = "in_time")
    private LocalDateTime inTime;

    @Column(name = "out_time")
    private LocalDateTime outTime;

    //@Comment("seconds 값만")
    @Column(name = "stay_time")
    private String stayTime;


}
