package com.example.api.repository;

import com.example.api.domain.Acesso;
import org.springframework.data.repository.CrudRepository;

public interface AcessoRepository extends CrudRepository<Acesso, Long> {

    Acesso findByUsername(String username);
}
