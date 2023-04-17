package com.neighbor.hair.service;

import com.neighbor.hair.constraints.legends.HairLocation;
import com.neighbor.hair.constraints.legends.StepName;
import com.neighbor.hair.entity.Action;
import com.neighbor.hair.entity.Application;
import com.neighbor.hair.entity.HairRoutine;
import com.neighbor.hair.entity.Product;
import com.neighbor.hair.entity.Step;
import com.neighbor.hair.repository.HairRoutineRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javassist.NotFoundException;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HairRoutineServiceImpl implements HairRoutineService{

  @Autowired
  private HairRoutineRepository hairRoutineRepository;

  public HairRoutineServiceImpl(HairRoutineRepository hairRoutineRepository) {
    this.hairRoutineRepository = hairRoutineRepository;
  }

  @Override
  public List<HairRoutine> findAll(){
    return hairRoutineRepository.findAll();
  }

  @Override
  public Optional<HairRoutine> findById(Long id){
    return hairRoutineRepository.findById(id);
  }

  @Override
  public Optional<HairRoutine> save(HairRoutine hairRoutine){
    return Optional.of(hairRoutineRepository.save(hairRoutine));
  }

  @Override
  public Optional<HairRoutine> update(Long id, HairRoutine hairRoutine) throws NotFoundException, IllegalArgumentException {

    if( !id.equals(hairRoutine.getId()))
      throw new IllegalArgumentException(
          "Id provided doesn't match the object id: {"+ id +"} vs {"+ hairRoutine.getId() +"}");

    Optional<HairRoutine> persistedHairRoutine = hairRoutineRepository.findById(id);

    if(!persistedHairRoutine.isPresent())
      throw new NotFoundException("Hair Routine with id {" + id + "} was not found");

    return Optional.of(hairRoutineRepository.save(hairRoutine));
  }

  @Override
  public void delete(Long id) throws NotFoundException{
    if(!(hairRoutineRepository.deleteByIdCount(id) > 0)){
      throw new NotFoundException("Hair Routine with id {" + id + "} was not found");
    }
  }

  @PostConstruct
  private void defaultSetup(){
    if( hairRoutineRepository.count() == 0){

      List<Application> applications = new ArrayList<>();

      Product product = new Product("Cantu Butter", "856017000027");

      Action action = new Action("Circular Motions", HairLocation.SCALP.name());

      Step stepOne = new Step(StepName.CLEANSE.name());
      Step stepTwo = new Step(StepName.FINISH.name());

      HairRoutine hr = new HairRoutine(7);

      Application app = new Application(product, action);

      applications.add(app);

      stepOne.setApplications(applications);
      stepTwo.setApplications(applications);

      List<Step> steps = new ArrayList<>();

      steps.add(stepOne);
      steps.add(stepTwo);

      hr.setSteps(steps);

      hairRoutineRepository.save(hr);
    }
  }

}
