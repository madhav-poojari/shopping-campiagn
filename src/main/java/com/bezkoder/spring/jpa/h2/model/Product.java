package com.bezkoder.spring.jpa.h2.model;
import jakarta.persistence.*;



@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "mrp")
    private double mrp;

    @Column(name = "current_price")
    private double currentPrice;

    @Column(name = "discount")
    private double discount;

    @Column(name = "inventory")
    private int inventory;

    public Product() {

    }

    public Product(double mrp, double currentPrice, double discount, int inventory) {
        this.mrp = mrp;
        this.currentPrice = currentPrice;
        this.discount = discount;
        this.inventory = inventory;
    }

    public long getId() {
        return id;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", mrp=" + mrp + ", currentPrice=" + currentPrice +
                ", discount=" + discount + ", inventory=" + inventory + "]";
    }
}
