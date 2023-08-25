package com.bezkoder.spring.jpa.h2.service;
import com.bezkoder.spring.jpa.h2.model.*;
import com.bezkoder.spring.jpa.h2.repository.CampaignDiscountRepository;
import com.bezkoder.spring.jpa.h2.repository.CampaignRepository;
import com.bezkoder.spring.jpa.h2.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CampaignDiscountRepository campaignDiscountRepository;
    @Autowired
    private CampaignRepository campaignRepository;

    public List<ProductWithCampaignDiscount> getProductHistoryWithCampaignDiscounts(long productId) {
        List<ProductWithCampaignDiscount> productHistory = new ArrayList<>();

        Product initialProduct = productRepository.findById(productId);


        List<CampaignDiscount> campaignDiscounts = campaignDiscountRepository.findByProductId(Long.toString( productId));
        for (CampaignDiscount campaignDiscount : campaignDiscounts) {
            ProductWithCampaignDiscount productWithDiscount = applyDiscountToProduct(initialProduct, campaignDiscount);
            productHistory.add(productWithDiscount);
        }

        return productHistory;
    }

    private ProductWithCampaignDiscount applyDiscountToProduct(Product product, CampaignDiscount campaignDiscount) {
        double discount = Double.parseDouble(campaignDiscount.getDiscount());
        double retailPrice = product.getCurrentPrice();
        double discountedPrice = retailPrice * (1 - discount / 100);

        return createProductWithDiscount(product, campaignDiscount, discountedPrice);
    }

    private ProductWithCampaignDiscount createProductWithDiscount(Product product, CampaignDiscount campaignDiscount, Double campaignPrice) {
        ProductWithCampaignDiscount productWithDiscount = new ProductWithCampaignDiscount();
        productWithDiscount.setId(product.getId());
        productWithDiscount.setCurrentPrice(product.getCurrentPrice());
        productWithDiscount.setCampaignPrice(campaignPrice);
        productWithDiscount.setDiscount(campaignDiscount != null ? Double.parseDouble(campaignDiscount.getDiscount()) : null);
        productWithDiscount.setStartDate(campaignDiscount != null ? campaignDiscount.getCampaign().getStartDate() : null);
        productWithDiscount.setEndDate(campaignDiscount != null ? campaignDiscount.getCampaign().getEndDate() : null);
        productWithDiscount.setCampaignTitle(campaignDiscount != null ? campaignDiscount.getCampaign().getTitle() : null);

        return productWithDiscount;
    }


    public List<ProductWithCurrentPrice> getProductPricesWithCampaignDiscounts() {
        List<ProductWithCurrentPrice> productPrices = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String currentDateStr = sdf.format(new Date());


        Campaign activeCampaign = campaignRepository.findActiveCampaign(currentDateStr);
        List<CampaignDiscount> activeCampaignDiscounts = campaignDiscountRepository.findByCampaignId(activeCampaign.getId());

        List<Product> products = productRepository.findAll();
        for (Product product : products) {
            Double currentPrice = applyCampaignDiscounts(product, activeCampaignDiscounts);
            productPrices.add(new ProductWithCurrentPrice(product.getId(), currentPrice));
        }

        return productPrices;
    }

    private Double applyCampaignDiscounts(Product product, List<CampaignDiscount> activeCampaignDiscounts) {
        Double currentPrice = product.getCurrentPrice();

        for (CampaignDiscount campaignDiscount : activeCampaignDiscounts) {
            if (campaignDiscount.getProductId().equals(product.getId())) {
                double discount = Double.parseDouble(campaignDiscount.getDiscount());
                currentPrice *= (1 - discount / 100);
                break; // Once discount is applied, exit the loop
            }
        }

        return currentPrice;
    }


}
