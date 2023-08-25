package com.bezkoder.spring.jpa.h2.model;
public class ProductWithCampaignDiscount {
    private long id;
    private double currentPrice;
    private Double campaignPrice;
    private Double discount;
    private String startDate;
    private String endDate;
    private String campaignTitle;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Double getCampaignPrice() {
        return campaignPrice;
    }

    public void setCampaignPrice(Double campaignPrice) {
        this.campaignPrice = campaignPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCampaignTitle() {
        return campaignTitle;
    }

    public void setCampaignTitle(String campaignTitle) {
        this.campaignTitle = campaignTitle;
    }
}
