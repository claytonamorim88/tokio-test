package com.example.api.service;

import com.example.api.domain.Endereco;
import com.example.api.repository.CustomerRepository;
import com.example.api.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private EnderecoRepository enderecoRepository;

    @Autowired
    public EnderecoService(EnderecoRepository repository) {
        this.enderecoRepository = repository;
    }

    public void insertEndereco(Endereco endereco){
        enderecoRepository.save(endereco);
    }

    public List<Endereco> findAll(){
        return enderecoRepository.findAllByOrderById();
    }

}
