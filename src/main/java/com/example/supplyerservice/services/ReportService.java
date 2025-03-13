package com.example.supplyerservice.services;

import com.example.supplyerservice.dto.ReportDTO;
import com.example.supplyerservice.models.SupplyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.supplyerservice.repositories.SupplyItemRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final SupplyItemRepository supplyItemRepository;

    @Autowired
    public ReportService(SupplyItemRepository supplyItemRepository) {
        this.supplyItemRepository = supplyItemRepository;
    }

    public List<ReportDTO> getReport(LocalDate startDate, LocalDate endDate) {
        List<SupplyItem> items = supplyItemRepository.findBySupplyDateBetween(startDate, endDate);

        Map<String, Map<String, ReportDTO>> reportMap = new HashMap<>();

        for (SupplyItem item : items) {
            String supplierName = item.getSupply().getSupplier().getName();
            String productName = item.getProduct().getName();

            reportMap.putIfAbsent(supplierName, new HashMap<>());
            Map<String, ReportDTO> productMap = reportMap.get(supplierName);

            productMap.compute(productName, (k, v) -> v == null
                    ? new ReportDTO(supplierName, productName, item.getWeight(), item.getTotalPrice())
                    : new ReportDTO(supplierName, productName, v.totalWeight() + item.getWeight(), v.totalPrice() + item.getTotalPrice()));
        }

        return reportMap.values().stream()
                .flatMap(m -> m.values().stream())
                .toList();
    }
}
