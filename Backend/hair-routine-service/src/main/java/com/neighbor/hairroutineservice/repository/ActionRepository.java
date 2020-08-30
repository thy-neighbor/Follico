package com.neighbor.hairroutineservice.repository;

import com.neighbor.hairroutineservice.entity.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends JpaRepository<Action,Integer> {
}
