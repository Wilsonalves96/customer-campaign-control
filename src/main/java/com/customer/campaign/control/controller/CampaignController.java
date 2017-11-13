package com.customer.campaign.control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.customer.campaign.control.client.CampaignClient;
import com.customer.campaign.control.model.Campaign;
import com.customer.campaign.control.repository.CampaignRepository;
import com.customer.campaign.control.request.model.CampaignRequestModel;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;

@RestController
@RequestMapping("/api/campaign/")
public class CampaignController {

	@Autowired
	CampaignRepository campaignRepository;

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@GetMapping("/")
	public Set<Campaign> getAllCampaigns() {
		Date currentDate = new Date();

		return campaignRepository.findByEndDateGreaterThan(currentDate);
	}

	@GetMapping("/{campaignId}")
	public ResponseEntity<Campaign> getCampaignBycampaignId(@PathVariable(value = "campaignId") Long campaignId) {
		Date currentDate = new Date();

		Campaign campaign = campaignRepository.findByCampaignIdAndEndDateGreaterThan(campaignId, currentDate);

		if (campaign == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(campaign);
	}
	
	private void updateExistingCampaigns(Campaign campaign) {
		Date currentDate = new Date();
		
		Campaign existingCampaign = campaignRepository.findByEndDateAndEndDateGreaterThan(campaign.getEndDate(),
				currentDate);

		if (existingCampaign != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(existingCampaign.getEndDate());
			c.add(Calendar.DATE, +1);

			existingCampaign.setEndDate(c.getTime());
			
			updateExistingCampaigns(existingCampaign);

			campaignRepository.save(existingCampaign);
		}		
	}

	@PostMapping("/")
	public Campaign createCampaign(@Valid @RequestBody CampaignRequestModel request) {
		Campaign campaign = new Campaign();
		campaign.setEndDate(request.getEndDate());
		campaign.setFavoriteTeamId(request.getFavoriteTeamId());
		campaign.setName(request.getName());
		campaign.setStartDate(request.getStartDate());
		
		updateExistingCampaigns(campaign);

		return campaignRepository.save(campaign);
	}

	@PutMapping("/{campaignId}")
	public ResponseEntity<Campaign> updateCampaign(@PathVariable(value = "campaignId") Long campaignId,
			@Valid @RequestBody CampaignRequestModel campaignDetails) throws IOException, URISyntaxException {
		Campaign campaign = campaignRepository.findOne(campaignId);

		if (campaign == null) {
			return ResponseEntity.notFound().build();
		}

		campaign.setName(campaignDetails.getName());
		campaign.setFavoriteTeamId(campaignDetails.getFavoriteTeamId());
		campaign.setStartDate(campaignDetails.getStartDate());
		campaign.setEndDate(campaignDetails.getEndDate());

		Campaign updatedCampaign = campaignRepository.save(campaign);

		CampaignClient client = new CampaignClient(campaign);

		client.notifySubscribers();

		return ResponseEntity.ok(updatedCampaign);
	}

	@DeleteMapping("/{campaignId}")
	public ResponseEntity<Campaign> deleteCampaign(@PathVariable(value = "campaignId") Long campaignId) {
		Campaign campaign = campaignRepository.findOne(campaignId);

		if (campaign == null) {
			return ResponseEntity.notFound().build();
		}

		campaignRepository.delete(campaign);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/notifyCampaignChange")
	public ResponseEntity<Campaign> notifyCampaignChange(@Valid @RequestBody CampaignRequestModel campaign) {
		return ResponseEntity.ok().build();
	}
}
