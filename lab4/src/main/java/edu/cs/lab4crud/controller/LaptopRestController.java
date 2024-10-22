package edu.cs.lab4crud.controller;

import edu.cs.lab4crud.model.Laptop;
import edu.cs.lab4crud.service.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/laptops")
@RequiredArgsConstructor
public class LaptopRestController {

    private final LaptopService laptopService;

    @GetMapping
    public ResponseEntity<List<Laptop>> getAllLaptops() {
        List<Laptop> laptops = laptopService.getAll();
        return ResponseEntity.ok(laptops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laptop> getLaptopById(@PathVariable String id) {
        Laptop laptop = laptopService.getById(id);
        return laptop != null ? ResponseEntity.ok(laptop) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Laptop> createLaptop(@RequestBody Laptop laptop) {
        Laptop createdLaptop = laptopService.create(laptop);
        return ResponseEntity.status(201).body(createdLaptop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Laptop> updateLaptop(@PathVariable String id, @RequestBody Laptop laptop) {
        laptop.setId(id);
        Laptop updatedLaptop = laptopService.update(laptop);
        return updatedLaptop != null ? ResponseEntity.ok(updatedLaptop) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaptop(@PathVariable String id) {
        laptopService.delById(id);
        return ResponseEntity.noContent().build();
    }
}
