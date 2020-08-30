package com.neighbor.hairroutineservice.repository;

import com.neighbor.hairroutineservice.entity.HairRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairRoutineRepository extends JpaRepository<HairRoutine,Integer> {
}
