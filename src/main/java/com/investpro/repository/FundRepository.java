package com.investpro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.investpro.entity.Fund;

public interface FundRepository extends JpaRepository<Fund, Long> {
}