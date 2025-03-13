package com.example.supplyerservice.dto;

public record ReportDTO(
        String supplierName,
        String productName,
        Double totalWeight,
        Double totalPrice
) {}
