package com.customer.campaign.control.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.customer.campaign.control.model.Campaign;
import com.customer.campaign.control.model.Customer;
import com.customer.campaign.control.repository.CampaignRepository;
import com.customer.campaign.control.repository.CustomerRepository;
import com.customer.campaign.control.request.model.CustomerRequestModel;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/customer/")
public class CustomerController {

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CampaignRepository campaignRepository;

	@GetMapping("/")
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "customerId") Long customerId) {
		Customer customer = customerRepository.findOne(customerId);

		if (customer == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok().body(customer);
	}

	@PostMapping("/")
	public Customer createCustomer(@Valid @RequestBody CustomerRequestModel request) {
		
		Customer customer = new Customer();
		
		customer.setBirthDate(request.getBirthDate());
		customer.setEmail(request.getEmail());
		customer.setFavoriteTeamId(request.getFavoriteTeamId());
		customer.setFullName(request.getFullName());

		try {
			Set<Campaign> campaigns = campaignRepository.findByFavoriteTeamId(request.getFavoriteTeamId());

			customer.setCampaigns(campaigns);
		} catch (Exception ex) {
			System.out.println(
					String.format("Não foi possível obter a lista de campanhas para esse time do coração, causa: %s",
							ex.getMessage()));
		}

		return customerRepository.save(customer);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "customerId") Long customerId,
			@Valid @RequestBody CustomerRequestModel customerDetails) {
		Customer customer = customerRepository.findOne(customerId);

		if (customer == null) {
			return ResponseEntity.notFound().build();
		}

		customer.setFullName(customerDetails.getFullName());
		customer.setEmail(customerDetails.getEmail());
		customer.setBirthDate(customerDetails.getBirthDate());
		customer.setFavoriteTeamId(customerDetails.getFavoriteTeamId());

		try {
			Set<Campaign> campaigns = campaignRepository.findByFavoriteTeamId(customer.getFavoriteTeamId());

			customer.getCampaigns().addAll(campaigns);
		} catch (Exception ex) {
			System.out.println(
					String.format("Não foi possível obter a lista de campanhas para esse time do coração, causa: %s",
							ex.getMessage()));
		}

		Customer updatedCustomer = customerRepository.save(customer);
		return ResponseEntity.ok(updatedCustomer);
	}

	@DeleteMapping("/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable(value = "customerId") Long customerId) {
		Customer customer = customerRepository.findOne(customerId);

		if (customer == null) {
			return ResponseEntity.notFound().build();
		}

		customerRepository.delete(customer);

		return ResponseEntity.ok().build();
	}
}
