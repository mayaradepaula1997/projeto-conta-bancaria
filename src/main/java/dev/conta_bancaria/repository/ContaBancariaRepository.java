package dev.conta_bancaria.repository;

import dev.conta_bancaria.entities.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository  extends JpaRepository<ContaBancaria, Long> {
}
