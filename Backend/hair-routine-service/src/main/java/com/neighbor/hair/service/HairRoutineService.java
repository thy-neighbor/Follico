package com.neighbor.hair.service;

import com.neighbor.hair.entity.HairRoutine;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;

public interface HairRoutineService {

  List<HairRoutine> findAll();

  Optional<HairRoutine> findById(Long id);

  Optional<HairRoutine> save(HairRoutine hairRoutine);

  Optional<HairRoutine> update(Long id, HairRoutine hairRoutine)
      throws NotFoundException, IllegalArgumentException;

  void delete(Long id) throws NotFoundException;
}