package dev.conta_bancaria.repository;

import dev.conta_bancaria.entities.Banco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BancoRepository extends JpaRepository<Banco, Long> {

    Optional<Banco> findByNome(String nome);


}
