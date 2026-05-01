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

    // ➤ Add Investment
    public Investment addInvestment(InvestmentRequest request) {
        Investment investment = new Investment();

        investment.setUserId(request.getUserId());
        investment.setFundId(request.getFundId());
        investment.setAmount(request.getAmount());
        investment.setDurationMonths(request.getDurationMonths());

        return investmentRepository.save(investment);
    }

    // ➤ Get User Investments
    public List<Investment> getInvestmentsByUser(Long userId) {
        return investmentRepository.findByUserId(userId);
    }

    // 🔥 ➤ DELETE INVESTMENT (NEW)
    public void deleteInvestment(Long id) {
        if (!investmentRepository.existsById(id)) {
            throw new RuntimeException("Investment not found");
        }
        investmentRepository.deleteById(id);
    }
}
