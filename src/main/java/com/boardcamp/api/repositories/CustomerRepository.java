package com.boardcamp.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerRepository, Long> {
    boolean existsByCpf(String cpf);

}
