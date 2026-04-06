package com.investpro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "funds")
public class Fund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fundName;
    private String category;
    private String riskLevel;
    private Double returnsPercentage;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFundName() { return fundName; }
    public void setFundName(String fundName) { this.fundName = fundName; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getRiskLevel() { return riskLevel; }
    public void setRiskLevel(String riskLevel) { this.riskLevel = riskLevel; }

    public Double getReturnsPercentage() { return returnsPercentage; }
    public void setReturnsPercentage(Double returnsPercentage) { this.returnsPercentage = returnsPercentage; }
}