package com.investpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investpro.entity.Fund;
import com.investpro.repository.FundRepository;

@Service
public class FundService {

    @Autowired
    private FundRepository fundRepository;

    public List<Fund> getAllFunds() {
        return fundRepository.findAll();
    }

    public Fund addFund(Fund fund) {
        return fundRepository.save(fund);
    }
}