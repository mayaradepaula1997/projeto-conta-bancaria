package dev.conta_bancaria.service;

import dev.conta_bancaria.dto.CriarBancoDto;
import dev.conta_bancaria.entities.Banco;
import dev.conta_bancaria.exception.OperacaoBancariaException;
import dev.conta_bancaria.repository.BancoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BancoService {


    private BancoRepository bancoRepository;

    public BancoService(BancoRepository bancoRepository) {
        this.bancoRepository = bancoRepository;
    }


    public Banco criarBanco(CriarBancoDto criarBancoDto){

        //Optional<Banco> bancoOptional = bancoRepository.findByNome(criarBancoDto.nome());


//        if(bancoOptional.isPresent())
//            throw new OperacaoBancariaException("Banco já existe");


        Banco banco = new Banco(criarBancoDto.nome());

        return bancoRepository.save(banco);

    }

    public List<Banco> listarBanco(){

       return bancoRepository.findAll();

    }
}
