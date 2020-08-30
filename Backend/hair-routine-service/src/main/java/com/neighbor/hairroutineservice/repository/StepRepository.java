package com.neighbor.hairroutineservice.repository;

import com.neighbor.hairroutineservice.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StepRepository extends JpaRepository<Step,Integer> {
}
