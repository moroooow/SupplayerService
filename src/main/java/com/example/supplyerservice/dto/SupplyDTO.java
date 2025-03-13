package com.example.supplyerservice.dto;

import java.time.LocalDate;
import java.util.List;

public class SupplyDTO {

    private Long supplierId;
    private LocalDate date;
    private List<SupplyItemDTO> products;  // Список продуктов для поставки

    // Конструкторы, геттеры и сеттеры

    public SupplyDTO(Long supplierId, LocalDate date, List<SupplyItemDTO> products) {
        this.supplierId = supplierId;
        this.date = date;
        this.products = products;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<SupplyItemDTO> getProducts() {
        return products;
    }

    public void setProducts(List<SupplyItemDTO> products) {
        this.products = products;
    }
}
