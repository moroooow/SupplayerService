package com.example.supplyerservice;


import com.example.supplyerservice.models.Product;
import com.example.supplyerservice.models.Supplier;
import com.example.supplyerservice.repositories.ProductRepository;
import com.example.supplyerservice.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataInitializer {

    private final SupplierRepository supplierRepository;
    private final ProductRepository ProductRepository;

    @Autowired
    public DataInitializer(SupplierRepository supplierRepository, ProductRepository productRepository) {
        this.supplierRepository = supplierRepository;
        this.ProductRepository = productRepository;
    }

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initializeProducts();
    }

    private void initializeProducts() {
        if (ProductRepository.count() == 0 && supplierRepository.count() == 0) {
            Supplier supplier1 = new Supplier("Поставщик 1");
            Supplier supplier2 = new Supplier("Поставщик 2");

            supplierRepository.saveAll(Arrays.asList(supplier1, supplier2));
            Product pear1 = new Product("Груша 1",supplier1 );
            Product pear2 = new Product("Груша 2", supplier2);
            Product apple1 = new Product("Яблоко 1", supplier1);
            Product apple2 = new Product("Яблоко 2", supplier2);

            ProductRepository.saveAll(Arrays.asList(pear1, pear2, apple1, apple2));
        }
    }
}
