package com.example.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.response.Response;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	private void validatingExistingData(@Valid Customer customer, BindingResult result) {

		this.service.findById(customer.getId())
				.ifPresent(ctm -> result.addError(new ObjectError("customer", "Customer already exists")));

	}

	@PostMapping("/register")
	public ResponseEntity<Response<Customer>> register(@Valid @RequestBody Customer customer, BindingResult result) {
		log.info("Saving a new customer.");
		Response<Customer> response = new Response<>();
		validatingExistingData(customer, result);

		if (result.hasErrors()) {
			log.info("Error! Validating id customer {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		this.service.register(customer);
		response.setData(customer);
		return ResponseEntity.ok(response);
	}
	

	@PutMapping("/upgradeCustomer/{id}")
	public ResponseEntity<Response<Customer>> upgrade(@PathVariable(name = "id") Long id,
			@Valid @RequestBody Customer customer, BindingResult result) {
		Response<Customer> response = new Response<>();
		Optional<Customer> ctm = service.findById(id);
		log.info("Upgrade customer.");

//		if (customer.getName().isEmpty() || customer.getName() == null) {
//			log.info("Preencha o nome.");
//			response.getErrors().add("Preencha o nome.");
//			return ResponseEntity.badRequest().body(response);
//		}

		if (ctm.get().getId().equals(customer.getId())) {
			if (result.hasErrors()) {
				result.getAllErrors().forEach(erro -> response.getErrors().add(erro.getDefaultMessage()));
				return ResponseEntity.badRequest().body(response);
			}
			this.service.upgrade(customer);
			response.setData(customer);

		}

		return ResponseEntity.ok(response);

	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response<Customer>> delete(@PathVariable(name = "id") Long id) {
		log.info("Deleting Customer");
		Response<Customer> response = new Response<>();
		Optional<Customer> ctm = this.service.findById(id);
		
		if (!ctm.isPresent()) {
			log.info("Error when deleting customer: " + id);
			response.getErrors().add("Error when deleting customer, customer not exists to the id: " + id);
			return ResponseEntity.badRequest().body(response);
		}
		
		this.service.delete(id);
		return ResponseEntity.ok(response);
	}

}
