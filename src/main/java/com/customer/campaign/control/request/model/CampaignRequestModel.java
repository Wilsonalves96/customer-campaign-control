package com.customer.campaign.control.request.model;

import java.util.Date;

public class CampaignRequestModel {

	private String name;

	private Long favoriteTeamId;

	private Date startDate;

	private Date endDate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getFavoriteTeamId() {
		return favoriteTeamId;
	}

	public void setFavoriteTeamId(Long favoriteTeamId) {
		this.favoriteTeamId = favoriteTeamId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
