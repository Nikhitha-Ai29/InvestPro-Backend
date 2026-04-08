package com.investpro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.investpro.entity.Fund;
import com.investpro.service.FundService;

@RestController
@RequestMapping("/funds")
@CrossOrigin(origins = "http://localhost:5173")
public class FundController {

    @Autowired
    private FundService fundService;

    @GetMapping
    public List<Fund> getAllFunds() {
        return fundService.getAllFunds();
    }

    @PostMapping
    public Fund addFund(@RequestBody Fund fund) {
        return fundService.addFund(fund);
    }
}