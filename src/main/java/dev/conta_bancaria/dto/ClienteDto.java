package dev.conta_bancaria.dto;

import dev.conta_bancaria.entities.Banco;

public record ClienteDto(Long id, String nome, Long bancoId) {
}
