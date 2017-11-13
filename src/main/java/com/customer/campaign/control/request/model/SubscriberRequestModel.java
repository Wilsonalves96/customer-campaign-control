package com.customer.campaign.control.request.model;

import java.util.Set;

public class SubscriberRequestModel {

	private String alias;

	private String url;

	private String parameters;

	private Set<Long> campaigns;

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParameters() {
		return parameters;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public Set<Long> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(Set<Long> campaigns) {
		this.campaigns = campaigns;
	}

}
