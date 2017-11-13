package com.customer.campaign.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.customer.campaign.control.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
