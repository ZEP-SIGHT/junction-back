package com.junction.tonight.spark.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity(name = "stayTimeTBL")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StayTime {

    @Id
    @Column
    @GeneratedValue
    private String stId;

    @Column
    private String vPlayerId;

    @Column
    private String vPlayerAuth;

    @Column
    private LocalDateTime inTime;

    @Column
    private LocalDateTime outTime;

    @Column
    private LocalDateTime stayTime;


}
