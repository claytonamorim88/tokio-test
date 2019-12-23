package com.example.api.repository;

//import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import com.example.api.domain.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {

	List<Customer> 	findAllByOrderByNameAsc();

	List<Customer> findAllByOrderByNameAsc(Pageable pageable);

}
