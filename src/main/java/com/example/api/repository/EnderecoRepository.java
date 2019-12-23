package com.example.api.repository;

import com.example.api.domain.Endereco;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EnderecoRepository extends CrudRepository<Endereco, Long> {

    List<Endereco> findAllByOrderById();
}
