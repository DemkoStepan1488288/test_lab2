package edu.cs.lab4crud.repository;

/*
  @author   StepanDemko
  @project   lab4
  @class  LaptopRepository
  @version  1.0.0
  @since 21.10.2024 - 22.02
*/

import edu.cs.lab4crud.model.Laptop;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepository extends MongoRepository<Laptop, String> {
}