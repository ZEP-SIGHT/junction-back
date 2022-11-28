package com.tonight.spark.repository;

import com.tonight.spark.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
    Map findMapByMapHash(String hash);
}
