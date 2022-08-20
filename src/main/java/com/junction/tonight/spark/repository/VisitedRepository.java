package com.junction.tonight.spark.repository;

import com.junction.tonight.spark.domain.Visited;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitedRepository extends JpaRepository<Visited, String> {
}
