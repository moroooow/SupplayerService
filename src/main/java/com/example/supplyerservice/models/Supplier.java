package com.example.supplyerservice.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="suppliers")
@AllArgsConstructor
public class Supplier {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Supplier() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, unique = true)
    private String name;


    public Supplier(String name){
        this.name = name;
    }
}
