package dev.conta_bancaria.repository;

import dev.conta_bancaria.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
