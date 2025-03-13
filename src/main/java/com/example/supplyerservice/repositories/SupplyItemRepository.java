package com.example.supplyerservice.repositories;

import com.example.supplyerservice.models.SupplyItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface SupplyItemRepository extends JpaRepository<SupplyItem, Long> {

    @Query("SELECT s FROM SupplyItem s WHERE s.supply.date BETWEEN :startDate AND :endDate")
    List<SupplyItem> findBySupplyDateBetween(@Param("startDate") LocalDate startDate,
                                             @Param("endDate") LocalDate endDate);
}
