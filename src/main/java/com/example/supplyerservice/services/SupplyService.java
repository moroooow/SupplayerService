package com.example.supplyerservice.services;

import com.example.supplyerservice.dto.SupplyDTO;
import com.example.supplyerservice.dto.SupplyItemDTO;
import jakarta.transaction.Transactional;
import com.example.supplyerservice.models.Product;
import com.example.supplyerservice.models.Supplier;
import com.example.supplyerservice.models.Supply;
import com.example.supplyerservice.models.SupplyItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.supplyerservice.repositories.ProductRepository;
import com.example.supplyerservice.repositories.SupplierRepository;
import com.example.supplyerservice.repositories.SupplyItemRepository;
import com.example.supplyerservice.repositories.SupplyRepository;

import java.util.List;

@Service
public class SupplyService {

    private final SupplyRepository supplyRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final SupplyItemRepository supplyItemRepository;

    @Autowired
    public SupplyService(SupplyRepository supplyRepository,
                         SupplierRepository supplierRepository,
                         ProductRepository productRepository,
                         SupplyItemRepository supplyItemRepository) {
        this.supplyRepository = supplyRepository;
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.supplyItemRepository = supplyItemRepository;
    }

    @Transactional
    public Supply createSupply(SupplyDTO supplyDTO) {
        Supplier supplier = supplierRepository.findById(supplyDTO.getSupplierId())
                .orElseThrow(() -> new RuntimeException("Поставщик не найден"));

        Supply supply = new Supply();
        supply.setSupplier(supplier);
        supply.setDate(supplyDTO.getDate());

        supply = supplyRepository.save(supply);

        for (SupplyItemDTO itemDTO : supplyDTO.getProducts()) {
            Product product = productRepository.findById(itemDTO.getProductId())
                    .orElseThrow(() -> new RuntimeException("Продукт не найден"));

            SupplyItem supplyItem = new SupplyItem();
            supplyItem.setSupply(supply);
            supplyItem.setProduct(product);
            supplyItem.setWeight(itemDTO.getWeight());
            supplyItem.setPricePerKg(itemDTO.getPricePerKg());

            supplyItemRepository.save(supplyItem);
        }

        return supply;
    }

    public List<Supply> getAllSupplies() {
        return supplyRepository.findAll();
    }
}
