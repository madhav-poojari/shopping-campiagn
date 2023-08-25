package com.bezkoder.spring.jpa.h2.controller;


import com.bezkoder.spring.jpa.h2.model.Campaign;
import com.bezkoder.spring.jpa.h2.model.CampaignDiscount;
import com.bezkoder.spring.jpa.h2.repository.CampaignRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CampaignController {

    @Autowired
    private CampaignRepository campaignRepository;

    @PostMapping("/campaign")
    public ResponseEntity<Campaign> createCampaign(@RequestBody Campaign campaign) {
        List<CampaignDiscount> campaignDiscounts = campaign.getCampaignDiscounts();
        if (campaignDiscounts != null) {
            for (CampaignDiscount campaignDiscount : campaignDiscounts) {
                campaignDiscount.setCampaign(campaign);
            }
        }

        Campaign createdCampaign = campaignRepository.save(campaign);
        return ResponseEntity.ok(createdCampaign);
    }

    @GetMapping("/campaign")
    public ResponseEntity<List<Campaign>> getAllCampaigns() {
        List<Campaign> campaigns = campaignRepository.findAll();
        return ResponseEntity.ok(campaigns);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Campaign> updateCampaign(@PathVariable Long id, @RequestBody Campaign campaign) {
        if (!campaignRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        campaign.setId(id); // Make sure the provided ID matches the path variable
        Campaign updatedCampaign = campaignRepository.save(campaign);
        return ResponseEntity.ok(updatedCampaign);
    }

    @DeleteMapping("/campaign/{id}")
    public ResponseEntity<String> deleteCampaign(@PathVariable Long id) {
        if (!campaignRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        campaignRepository.deleteById(id);
        return ResponseEntity.ok("Campaign deleted successfully");
    }
}
