package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private static final Logger log = LoggerFactory.getLogger(CustomerService.class);
	
	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return this.repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return this.repository.findById(id);
	}
	
	
	public Customer register(Customer customer) {
		log.info("Registrando novo Customer.");
		return this.repository.save(customer);
	}
	
	public Customer upgrade(Customer customer) {
		log.info("Atualizando Customer.");
		return this.repository.save(customer);
	}
	
	public void delete(Long id) {
		log.info("Excluindo Customer.");
		this.repository.deleteById(id);
	}

}
