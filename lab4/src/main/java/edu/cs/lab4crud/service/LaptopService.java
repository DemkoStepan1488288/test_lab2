package edu.cs.lab4crud.service;

/*
  @author   StepanDemko
  @project   lab4-crud
  @class  PhoneService
  @version  1.0.0
  @since 21.10.2024 - 23.59
*/

import edu.cs.lab4crud.model.Laptop;
import edu.cs.lab4crud.repository.LaptopRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LaptopService {
    private final LaptopRepository laptopRepository;
    private List<Laptop> laptops = new ArrayList<>();

    @PostConstruct
    void init() {
        this.laptopRepository.deleteAll();
        this.laptopRepository.saveAll(this.laptops);
    }

    public List<Laptop> getAll() {
        return this.laptopRepository.findAll();
    }

    public Laptop getById(String id) {
        return this.laptopRepository.findById(id).orElse(null);
    }

    public Laptop create(Laptop laptop) {
        return this.laptopRepository.save(laptop);
    }

    public Laptop update(Laptop laptop) {
        return this.laptopRepository.save(laptop);
    }

    public void delById(String id) {
        this.laptopRepository.deleteById(id);
    }

    public LaptopService(final LaptopRepository laptopRepository) {
        this.laptops.add(new Laptop("MacBook Pro", "Apple", "Powerful laptop with M1 chip", 16, 512, 1999.99));
        this.laptops.add(new Laptop("Dell XPS 13", "Dell", "Compact and powerful", 16, 256, 1499.99));
        this.laptops.add(new Laptop("HP Spectre x360", "HP", "Convertible laptop with touch screen", 8, 512, 1399.99));
        this.laptopRepository = laptopRepository;
    }
}
