package com.example.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public boolean isCustomerExist(Long id){
		if(repository.existsById(id))
			return true;
		else
			return false;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public List<Customer> findAllPaging(int pagina, int tamanho){
		Pageable pageable = PageRequest.of(pagina, tamanho);
		return repository.findAllByOrderByNameAsc(pageable);
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	public boolean deleteById(Long id){
		if(! repository.existsById(id))
			throw new IllegalArgumentException("Customer de id=" + id + " inexistente. Portanto não será excluído");

		if(repository.existsById(id)){
			repository.deleteById(id);
			return true;
		}else
			return false;
	}

	public void updateCustomer(Customer customer){
		if(! repository.existsById(customer.getId()))
			throw new IllegalArgumentException("Customer de id=" + customer.getId() + " inexistente. Portanto não será alterado");

		Customer retorno = repository.save(customer);
	}

	public boolean insertCustomer(Customer customer){
		if(repository.existsById(customer.getId()))
			return false;

		Customer retorno = repository.save(customer);
		if(retorno == null)
			return false;
		else
			return true;
	}

}
