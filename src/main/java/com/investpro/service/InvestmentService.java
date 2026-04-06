package com.investpro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.investpro.dto.InvestmentRequest;
import com.investpro.entity.Investment;
import com.investpro.repository.InvestmentRepository;

@Service
public class InvestmentService {

    @Autowired
    private InvestmentRepository investmentRepository;

    public Investment addInvestment(InvestmentRequest request) {
        Investment investment = new Investment();
        investment.setUserId(request.getUserId());
        investment.setFundId(request.getFundId());
        investment.setAmount(request.getAmount());
        investment.setDurationMonths(request.getDurationMonths());

        return investmentRepository.save(investment);
    }

    public List<Investment> getInvestmentsByUser(Long userId) {
        return investmentRepository.findByUserId(userId);
    }
}