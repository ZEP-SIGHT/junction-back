package com.junction.tonight.spark.repository;

import com.junction.tonight.spark.domain.ZepMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MapRepository extends JpaRepository<ZepMap, Long> {
    ZepMap findMapByMapHash(String hash);
}
