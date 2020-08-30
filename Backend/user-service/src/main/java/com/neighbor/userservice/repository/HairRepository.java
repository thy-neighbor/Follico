package com.neighbor.userservice.repository;

import com.neighbor.userservice.entity.Hair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HairRepository extends JpaRepository<Hair,Integer> {
}
