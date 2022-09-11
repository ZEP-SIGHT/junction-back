package com.junction.tonight.spark.repository;

import com.junction.tonight.spark.domain.StayTime;
import com.junction.tonight.spark.dto.page1.TestInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StayTimeRepository extends JpaRepository<StayTime, String> {

    List<StayTime> findStayTimeByMapHash(String hash);

    @Query(nativeQuery = true,
            value =
                    "SELECT "
                            + "map_hash"
                            + ",designated_area_name"
                            + ",v_player_auth"
                            + ", COUNT(*) as cnt "
                            + "FROM stay_timetbl as st "
                            + "GROUP BY map_hash, v_player_auth, designated_area_name "
                            + "ORDER BY designated_area_name, v_player_auth"
    )
    List<TestInterface> findTestInterfaceByNativeQuery();

    @Query(nativeQuery = true,
            value =
                    "SELECT COUNT(*)" +
                            " FROM stay_timetbl\n" +
                            " WHERE designated_area_name = ?1 AND v_player_auth = ?2 AND map_hash = ?3"
    )
    Integer getAuthCountForGroup(String areaName, String vPAuth, String mapHash);

    @Query(nativeQuery = true,
            value =
                    "SELECT SUM(stay_time)" +
                            " FROM stay_timetbl" +
                            " WHERE designated_area_name = ?1 AND v_player_auth = ?2 AND map_hash = ?3"
    )
    Integer getStayTimeSumForGroup(String areaName, String vPAuth, String mapHash);

}
