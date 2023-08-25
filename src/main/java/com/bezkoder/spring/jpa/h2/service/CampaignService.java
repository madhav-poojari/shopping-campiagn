package com.bezkoder.spring.jpa.h2.service;
import com.bezkoder.spring.jpa.h2.model.CampaignDiscount;
import com.bezkoder.spring.jpa.h2.repository.CampaignDiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampaignService {

    @Autowired
    private CampaignDiscountRepository campaignDiscountRepository;

    public List<CampaignDiscount> getCampaignDiscountHistoryByProductId(String productId) {
        return campaignDiscountRepository.findByProductId(productId);
    }
}
