package com.junction.tonight.spark.repository;

import com.junction.tonight.spark.domain.Map;
import com.junction.tonight.spark.domain.StayTime;
import com.junction.tonight.spark.domain.Visited;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VisitedRepository extends JpaRepository<Visited, String> {
    //    List<StayTime> findStayTimeByMap(Map map);
//    List<Visited> findVisitedByMap(Map map);
    List<Visited> findVisitedByMapHash(String map);

}
