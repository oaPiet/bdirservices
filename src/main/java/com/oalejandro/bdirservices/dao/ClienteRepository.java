package com.oalejandro.bdirservices.dao;

import com.oalejandro.bdirservices.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
