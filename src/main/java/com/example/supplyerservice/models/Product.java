package com.example.supplyerservice.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="products")
@AllArgsConstructor
public class Product {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public Product() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    public Product(String name, Supplier supplier){
        this.name = name;
        this.supplier = supplier;
    }
}
