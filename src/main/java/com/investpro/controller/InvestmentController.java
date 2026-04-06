package com.investpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.investpro.dto.InvestmentRequest;
import com.investpro.entity.Investment;
import com.investpro.service.InvestmentService;

@RestController
@RequestMapping("/investments")
@CrossOrigin(origins = "*")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    @PostMapping("/add")
    public Investment addInvestment(@RequestBody InvestmentRequest request) {
        return investmentService.addInvestment(request);
    }

    @GetMapping("/user/{userId}")
    public List<Investment> getUserInvestments(@PathVariable Long userId) {
        return investmentService.getInvestmentsByUser(userId);
    }
}