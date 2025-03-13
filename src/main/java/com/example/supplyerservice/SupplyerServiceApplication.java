package com.example.supplyerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.example.supplyerservice")
public class SupplyerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SupplyerServiceApplication.class, args);
    }

}
  