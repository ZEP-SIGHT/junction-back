package com.tonight.spark.repository;

import com.tonight.spark.domain.Visited;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitedRepository extends JpaRepository<Visited, String> {
    //    List<StayTime> findStayTimeByMap(Map map);
//    List<Visited> findVisitedByMap(Map map);
    List<Visited> findVisitedByMapHash(String map);



//    SELECT
//    map_hash, designated_area_name, v_player_auth, COUNT(*) as cnt
//    FROM stay_timetbl
//    GROUP BY map_hash, v_player_auth, designated_area_name
//    ORDER BY designated_area_name, v_player_auth;
}
