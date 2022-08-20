package com.junction.tonight.spark.repository;

import com.junction.tonight.spark.domain.StayTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StayTimeRepository extends JpaRepository<StayTime, String> {
}
