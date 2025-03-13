package com.example.supplyerservice.contollers;

import com.example.supplyerservice.dto.SupplyDTO;
import com.example.supplyerservice.dto.SupplyItemDTO;
import com.example.supplyerservice.models.Product;
import com.example.supplyerservice.models.Supplier;
import com.example.supplyerservice.models.Supply;
import com.example.supplyerservice.models.SupplyItem;
import com.example.supplyerservice.services.ProductService;
import com.example.supplyerservice.services.SupplierService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.supplyerservice.services.SupplyService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/supplies")
public class SupplyController {

    private final SupplyService supplyService;

    private final SupplierService supplierService;  // Сервис для работы с поставщиками
    private final ProductService productService;  // Сервис для работы с продуктами


    @Autowired
    public SupplyController(SupplyService supplyService,
                            SupplierService supplierService,
                            ProductService productService) {
        this.supplyService = supplyService;
        this.supplierService = supplierService;
        this.productService = productService;
    }

    @GetMapping
    public String getAllSupplies(HttpSession session, Model model) {
        List<Supplier> suppliers = supplierService.getAllSuppliers();
        List<Product> products = productService.getAllProducts();

        String token = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", token);
        model.addAttribute("products", products);
        model.addAttribute("suppliers", suppliers);
        return "supplies";
    }

    @PostMapping("/add")
    public String addSupply(@RequestParam Long supplierId,
                            @RequestParam LocalDate date,
                            @RequestParam List<Long> productIds,
                            @RequestParam List<Double> weights,
                            @RequestParam List<Double> prices) {

        List<SupplyItemDTO> items = new ArrayList<>();
        for(int i = 0; i < productIds.size(); i ++){
            items.add(new SupplyItemDTO(productIds.get(i), weights.get(i), prices.get(i)));
        }

        SupplyDTO supplyDTO = new SupplyDTO(supplierId, date,items);
        supplyService.createSupply(supplyDTO);
        return "redirect:/supplies";
    }
}
