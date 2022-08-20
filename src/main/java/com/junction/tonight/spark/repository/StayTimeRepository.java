package com.junction.tonight.spark.repository;

import com.junction.tonight.spark.domain.Map;
import com.junction.tonight.spark.domain.StayTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StayTimeRepository extends JpaRepository<StayTime, String> {

    List<StayTime> findStayTimeByMapHash(String hash);

}
