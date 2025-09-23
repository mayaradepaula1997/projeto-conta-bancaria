package dev.conta_bancaria.service;


import dev.conta_bancaria.entities.ContaBancaria;
import dev.conta_bancaria.repository.ContaBancariaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaBancariaService {

    private ContaBancariaRepository contaBancariaRepository;

    //INJEÇÃO DE DEPENDENCIA
    public ContaBancariaService(ContaBancariaRepository contaBancariaRepository) {
        this.contaBancariaRepository = contaBancariaRepository;
    }


    //MÉTODO DE SACAR

    public boolean sacar(Long id, double valor) {

        Optional<ContaBancaria> contaBancaria = contaBancariaRepository.findById(id);

        if (contaBancaria.isPresent()) {
            ContaBancaria conta = contaBancaria.get();
            conta.sacar(valor);
            contaBancariaRepository.save(conta);

            return true;
        }

        return false;
    }

    public boolean depositar (Long id, double valor){

        Optional<ContaBancaria> contaBancaria = contaBancariaRepository.findById(id);

        if (contaBancaria.isPresent()){
            ContaBancaria conta = contaBancaria.get();
            conta.depositar(valor);
            contaBancariaRepository.save(conta);
            return true;

        }

        return  false;
    }

}