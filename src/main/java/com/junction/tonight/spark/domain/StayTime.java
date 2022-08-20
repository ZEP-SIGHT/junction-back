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
    private Long stId;

    @Column
    private String mapHash;

    @Column
    private String designatedAreaName;

    @Column
    private String vPlayerId;

    @Column
    private String vPlayerAuth;

    @Column
    private LocalDateTime inTime;

    @Column
    private LocalDateTime outTime;

    @Column
    //@Comment("seconds 값만")
    private String stayTime;


}
