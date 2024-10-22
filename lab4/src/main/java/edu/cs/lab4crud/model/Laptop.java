package edu.cs.lab4crud.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Document
public class Laptop {
    private String id;
    private String model;
    private String brand;
    private String description;
    private int ram; // in GB
    private int storage; // in GB
    private double price;

    public Laptop(String model, String brand, String description, int ram, int storage, double price) {
        this.model = model;
        this.brand = brand;
        this.description = description;
        this.ram = ram;
        this.storage = storage;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return Objects.equals(id, laptop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
