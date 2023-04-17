package com.neighbor.hair.repository;

import com.neighbor.hair.entity.HairRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairRoutineRepository extends JpaRepository<HairRoutine,Long> {
  Long deleteByIdCount(Long id);
}
