package com.junction.tonight.spark.repository;

import com.junction.tonight.spark.domain.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
    Map findMapByMapHash(String hash);
}
