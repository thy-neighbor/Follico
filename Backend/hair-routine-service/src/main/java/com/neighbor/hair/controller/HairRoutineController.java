package com.neighbor.hair.controller;

import com.neighbor.hair.entity.HairRoutine;
import com.neighbor.hair.service.HairRoutineService;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/hair/api")
public class HairRoutineController {

  @Autowired
  HairRoutineService hairRoutineService;

  @GetMapping("/{id}")
  public ResponseEntity<Optional<HairRoutine>> read(@PathVariable Long id){

    Optional<HairRoutine> data = hairRoutineService.findById(id);

    return data.isPresent() ? new ResponseEntity<>(data, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping()
  public ResponseEntity<List<HairRoutine>> readAll(){

    List<HairRoutine> data = hairRoutineService.findAll();

    return data.isEmpty() ? new ResponseEntity<>(HttpStatus.NOT_FOUND) : new ResponseEntity<>(data, HttpStatus.OK);
  }

  @PostMapping()
  public ResponseEntity<?> create(@Valid @RequestBody HairRoutine hairRoutine, final BindingResult bindingResult){

    if(!bindingResult.hasErrors()){
      Optional<HairRoutine> data = hairRoutineService.save(hairRoutine);

      if(data.isPresent()){
        return new ResponseEntity<>(data.get() , HttpStatus.CREATED);
      }
    }

    return new ResponseEntity<>(bindingResult.getAllErrors() , HttpStatus.BAD_REQUEST);
  }

  @PutMapping()
  public ResponseEntity<?> edit(
      @PathVariable Long id , @Valid @RequestBody HairRoutine hairRoutine, final BindingResult bindingResult){

    if(!bindingResult.hasErrors()){

      Optional<HairRoutine> data = Optional.empty();

      try{
        data = hairRoutineService.update(id, hairRoutine);
      } catch(Exception e){
        e.printStackTrace();
      }

      if(data.isPresent()){
        return new ResponseEntity<>(data.get() , HttpStatus.CREATED);
      }
    }
    return new ResponseEntity<>(bindingResult.getAllErrors() , HttpStatus.BAD_REQUEST);
  }

  @DeleteMapping()
  public ResponseEntity<?> delete(@PathVariable Long id){

    try{
      hairRoutineService.delete(id);
    } catch(NotFoundException e){
      e.printStackTrace();
    }

    return ResponseEntity.noContent().build();
  }
}