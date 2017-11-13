package com.customer.campaign.control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.customer.campaign.control.model.Campaign;
import com.customer.campaign.control.model.Subscriber;
import com.customer.campaign.control.repository.CampaignRepository;
import com.customer.campaign.control.repository.SubscriberRepository;
import com.customer.campaign.control.request.model.SubscriberRequestModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/subscriber/")
public class SubscriberController {

	@Autowired
	CampaignRepository campaignRepository;

	@Autowired
	SubscriberRepository subscriberRepository;

	@GetMapping("/")
	public List<Subscriber> getAllSubscribers() {
		return subscriberRepository.findAll();
	}

	@GetMapping("/{subscriberId}")
	public ResponseEntity<Subscriber> getSubscriberById(@PathVariable(value = "subscriberId") Long subscriberId) {

		Subscriber subscriber = subscriberRepository.findOne(subscriberId);

		if (subscriber == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok().body(subscriber);
	}

	private Set<Campaign> getCampaigns(Set<Long> campaigns) {
		Set<Campaign> relatedCampaigns = new HashSet<Campaign>();

		for (Long campaignId : campaigns) {
			Campaign relatedCampaign = campaignRepository.findOne(campaignId);

			if (relatedCampaign != null) {
				relatedCampaigns.add(relatedCampaign);
			}
		}

		return relatedCampaigns;
	}

	@PostMapping("/")
	public Subscriber createSubscriber(@Valid @RequestBody SubscriberRequestModel request) {
		Subscriber subscriber = new Subscriber();

		subscriber.setAlias(request.getAlias());
		subscriber.setParameters(request.getParameters());
		subscriber.setUrl(request.getUrl());

		Set<Campaign> campaigns = getCampaigns(request.getCampaigns());

		subscriber.setCampaigns(campaigns);

		return subscriberRepository.save(subscriber);
	}

	@PutMapping("/{subscriberId}")
	public ResponseEntity<Subscriber> updateSubscriber(@PathVariable(value = "subscriberId") Long subscriberId,
			@Valid @RequestBody SubscriberRequestModel subscriberDetails) {
		Subscriber subscriber = subscriberRepository.findOne(subscriberId);

		if (subscriber == null) {
			return ResponseEntity.notFound().build();
		}

		subscriber.setAlias(subscriberDetails.getAlias());
		subscriber.setUrl(subscriberDetails.getUrl());
		subscriber.setParameters(subscriberDetails.getParameters());
		
		subscriber.getCampaigns().addAll(getCampaigns(subscriberDetails.getCampaigns()));

		Subscriber updatedSubscriber = subscriberRepository.save(subscriber);

		return ResponseEntity.ok(updatedSubscriber);
	}

	@DeleteMapping("/{subscriberId}")
	public ResponseEntity<Subscriber> deleteSubscriber(@PathVariable(value = "subscriberId") Long subscriberId) {
		Subscriber subscriber = subscriberRepository.findOne(subscriberId);

		if (subscriber == null) {
			return ResponseEntity.notFound().build();
		}

		subscriberRepository.delete(subscriber);

		return ResponseEntity.ok().build();
	}
}
