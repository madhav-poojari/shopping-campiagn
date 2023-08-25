package com.bezkoder.spring.jpa.h2.model;

public class ProductWithCurrentPrice {
    private long id;
    private double currentPrice;

    public ProductWithCurrentPrice(long id, double currentPrice) {
        this.id = id;
        this.currentPrice = currentPrice;
    }

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
}
