package com.customer.campaign.control.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "b_campaign")
@EntityListeners(AuditingEntityListener.class)
public class Campaign {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long campaignId;

	@NotBlank
	private String name;

	private Long favoriteTeamId;

	private Date startDate;

	private Date endDate;

	@ManyToMany(mappedBy = "campaigns")
	@JsonBackReference(value = "customer")
	private Set<Customer> customers;

	@ManyToMany(mappedBy = "campaigns")
	@JsonBackReference(value = "subscriber")
	private Set<Subscriber> subscribers;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	public Long getCampaignId() {
		return campaignId;
	}

	public void setId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public Long getFavoriteTeamId() {
		return favoriteTeamId;
	}

	public Set<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}

	public Set<Subscriber> getSubscribers() {
		return subscribers;
	}

	public void setSubscribers(Set<Subscriber> subscribers) {
		this.subscribers = subscribers;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public void setFavoriteTeamId(Long favoriteTeamId) {
		this.favoriteTeamId = favoriteTeamId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
