package com.example.supplyerservice.dto;

public class SupplyItemDTO{

    private Long productId;
    private Double weight;
    private Double pricePerKg;

    public SupplyItemDTO(Long productId, Double weight, Double pricePerKg) {
        this.productId = productId;
        this.weight = weight;
        this.pricePerKg = pricePerKg;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(Double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }



}
