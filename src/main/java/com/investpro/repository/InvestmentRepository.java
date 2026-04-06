package com.investpro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.investpro.entity.Investment;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByUserId(Long userId);
}