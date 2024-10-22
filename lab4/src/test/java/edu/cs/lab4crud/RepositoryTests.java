package edu.cs.lab4crud;

/*
  @author   DovhalKyrylo
  @project   lab6
  @class  RepositoryTest
  @version  1.0.0
  @since 22.10.24 - 19.48
*/
import edu.cs.lab4crud.model.Laptop;
import edu.cs.lab4crud.repository.LaptopRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class RepositoryTests {

    @Autowired
    private LaptopRepository laptopRepository;

    private Laptop laptop1;
    private Laptop laptop2;

    @BeforeEach
    void setUp() {
        laptop1 = new Laptop("1", "MacBook Pro", "Apple", "Powerful laptop with M1 chip", 16, 512, 1999.99);
        laptop2 = new Laptop("2", "Dell XPS 13", "Dell", "Compact and powerful", 16, 256, 1499.99);
        laptopRepository.save(laptop1);
        laptopRepository.save(laptop2);
    }

    @AfterEach
    void tearDown() {
        laptopRepository.deleteAll();
    }

    @Test
    void shouldCreateNewLaptop() {
        Laptop laptop3 = new Laptop("3", "HP Spectre x360", "HP", "Convertible laptop with touch screen", 8, 512, 1399.99);
        Laptop createdLaptop = laptopRepository.save(laptop3);

        assertNotNull(createdLaptop);
        assertNotNull(createdLaptop.getId()); // ID should be generated
        assertEquals(laptop3.getModel(), createdLaptop.getModel());
    }

    @Test
    void shouldRetrieveLaptopById() {
        Optional<Laptop> retrievedLaptop = laptopRepository.findById(laptop1.getId());

        assertTrue(retrievedLaptop.isPresent());
        assertEquals(laptop1.getModel(), retrievedLaptop.get().getModel());
    }

    @Test
    void shouldReturnEmptyOptionalForNonExistentLaptop() {
        Optional<Laptop> retrievedLaptop = laptopRepository.findById("non-existent-id");

        assertFalse(retrievedLaptop.isPresent());
    }

    @Test
    void shouldUpdateExistingLaptop() {
        laptop1.setPrice(1899.99);
        Laptop updatedLaptop = laptopRepository.save(laptop1);

        assertNotNull(updatedLaptop);
        assertEquals(1899.99, updatedLaptop.getPrice());
    }

    @Test
    void shouldDeleteLaptop() {
        laptopRepository.delete(laptop1);
        Optional<Laptop> deletedLaptop = laptopRepository.findById(laptop1.getId());

        assertFalse(deletedLaptop.isPresent());
    }

    @Test
    void shouldNotThrowErrorWhenDeletingNonExistentLaptop() {
        assertDoesNotThrow(() -> laptopRepository.deleteById("non-existent-id"));
    }

    @Test
    void shouldRetrieveAllLaptops() {
        List<Laptop> allLaptops = laptopRepository.findAll();

        assertNotNull(allLaptops);
        assertEquals(2, allLaptops.size());
    }

    @Test
    void shouldReturnEmptyListWhenNoLaptopsExist() {
        laptopRepository.deleteAll();
        List<Laptop> allLaptops = laptopRepository.findAll();

        assertNotNull(allLaptops);
        assertTrue(allLaptops.isEmpty());
    }
}
