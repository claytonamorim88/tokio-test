package com.example.api.web.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}

	@GetMapping ("/paginacao")
	public List<Customer> findAllPaging(@RequestParam int pagina, @RequestParam int tamanho){
		return service.findAllPaging(pagina, tamanho);
	}

	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@PostMapping()
    public ResponseEntity<String> insertCustomer(@Valid @RequestBody Customer customer){
		boolean isInserted = service.insertCustomer(customer);

		if(isInserted)
			return new ResponseEntity<>("", HttpStatus.CREATED);
		else
				return new ResponseEntity<>("", HttpStatus.OK);
	}

    @PutMapping()
    public ResponseEntity<String> updateCustomer(@Valid @RequestBody Customer customer){
		service.updateCustomer(customer);
		return new ResponseEntity<>("", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
	    service.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
