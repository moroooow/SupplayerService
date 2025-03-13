package com.example.supplyerservice.contollers;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @GetMapping
    public String getDashboard(HttpSession session, Model model) {
        String token = (String) session.getAttribute("jwtToken");
        model.addAttribute("jwtToken", token);
        return "dashboard";
    }
}
