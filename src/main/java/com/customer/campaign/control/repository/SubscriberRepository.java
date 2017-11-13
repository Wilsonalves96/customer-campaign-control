package com.customer.campaign.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.campaign.control.model.Subscriber;

public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {

}
