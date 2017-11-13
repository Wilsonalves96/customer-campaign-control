package com.customer.campaign.control.repository;

import java.util.Date;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import com.customer.campaign.control.model.Campaign;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
	Campaign findByCampaignIdAndEndDateGreaterThan(Long campaignId, Date currentDate);

	Campaign findByEndDateAndEndDateGreaterThan(Date endDate, Date currentDate);
	
	Set<Campaign> findByEndDateGreaterThan(Date currentDate);
	
	Set<Campaign> findByFavoriteTeamId(Long favoriteTeamId);
}
