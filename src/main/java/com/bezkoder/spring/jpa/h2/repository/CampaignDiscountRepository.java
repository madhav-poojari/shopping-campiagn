package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.model.CampaignDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CampaignDiscountRepository extends JpaRepository<CampaignDiscount, Long> {
    List<CampaignDiscount> findByProductId(String productId);

    List<CampaignDiscount> findByCampaignId(Long id);
}