package com.bezkoder.spring.jpa.h2.repository;

import com.bezkoder.spring.jpa.h2.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long> {
    // You can add custom query methods if needed
    @Query("SELECT c FROM Campaign c WHERE :currentDate >= c.startDate AND :currentDate <= c.endDate ORDER BY c.startDate DESC")
    Campaign findActiveCampaign(@Param("currentDate") String currentDate);


}
