package com.example.supplyerservice.contollers;

import com.example.supplyerservice.dto.ReportDTO;
import com.example.supplyerservice.services.ReportService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/reports")
public class ReportController {

    private final ReportService reportService;

    @Autowired
    public ReportController(ReportService reportService){
        this.reportService = reportService;
    }

    @GetMapping
    public String getDashboard(HttpSession session, Model model,
                               @RequestParam(defaultValue = "9999-01-01") LocalDate startDate, @RequestParam(defaultValue = "9999-01-01") LocalDate endDate) {
        List<ReportDTO> reports;
        if(Objects.equals(startDate, LocalDate.ofYearDay(9999, 1)) && Objects.equals(endDate, LocalDate.ofYearDay(9999, 1))){
            reports = reportService.getReport(LocalDate.now().minusDays(1), LocalDate.now());
        } else {
            reports = reportService.getReport(startDate, endDate);
        }

        String token = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", token);
        model.addAttribute("reports", reports);
        return "reports";
    }
}
