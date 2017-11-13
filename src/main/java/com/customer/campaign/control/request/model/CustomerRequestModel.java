package com.customer.campaign.control.request.model;

import java.util.Date;

public class CustomerRequestModel {

	private String fullName;

	private String email;

	private Date birthDate;

	private Long favoriteTeamId;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Long getFavoriteTeamId() {
		return favoriteTeamId;
	}

	public void setFavoriteTeamId(Long favoriteTeamId) {
		this.favoriteTeamId = favoriteTeamId;
	}
}
