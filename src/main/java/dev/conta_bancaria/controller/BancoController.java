package dev.conta_bancaria.controller;


import dev.conta_bancaria.dto.CriarBancoDto;
import dev.conta_bancaria.entities.Banco;
import dev.conta_bancaria.entities.Cliente;
import dev.conta_bancaria.service.BancoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/bancos")
public class BancoController {


    private BancoService bancoService;

    public BancoController(BancoService bancoService) {
        this.bancoService = bancoService;
    }

    @PostMapping
    public ResponseEntity<Banco> criacaoBanco(CriarBancoDto dto){

        Banco banco = bancoService.criarBanco(dto);

        return ResponseEntity.ok(banco);

    }

    @GetMapping
    public ResponseEntity<List<Banco>> listarBanco(){

        List<Banco> bancoList = bancoService.listarBanco();

       return ResponseEntity.ok(bancoList);
    }
}
