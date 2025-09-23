package dev.conta_bancaria.service;

import dev.conta_bancaria.dto.AtualizarCliente;
import dev.conta_bancaria.dto.ClienteDto;
import dev.conta_bancaria.entities.Banco;
import dev.conta_bancaria.entities.Cliente;
import dev.conta_bancaria.exception.OperacaoBancariaException;
import dev.conta_bancaria.repository.BancoRepository;
import dev.conta_bancaria.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    private BancoRepository bancoRepository;

    public ClienteService(ClienteRepository clienteRepository, BancoRepository bancoRepository) {
        this.clienteRepository = clienteRepository;
        this.bancoRepository = bancoRepository;
    }

    //MÉTODO PARA CRIAR O CLIENTE E ANTES VERIFICO SE O BANCO EXISTE NO BD

    public Cliente cliente (ClienteDto clienteDto){

       Optional<Banco> bancoOpcional = bancoRepository.findById(clienteDto.bancoId()); //busca o banco no BD

       Banco banco = bancoOpcional.orElseThrow(() //Se não achar lança a exceção
               -> new OperacaoBancariaException("Banco não existe com id: " + clienteDto.bancoId()));

       Cliente cliente = new Cliente(clienteDto.nome(), banco); //Se achar o banco, cria o cliente e passa o banco

       return clienteRepository.save(cliente); //salva o cliente no banco de dados

    }

    //MÉTODO PARA LISTA TODOS OS CLIENTES

    public List<Cliente> listarClientes(){

        return clienteRepository.findAll(); //Busca a lista de cliente no BD
    }

    //MÉTODO LISTAR PELO ID

    public Cliente clienteId (Long id){

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isPresent()) {

            return clienteOptional.get();
        }
        else {
            throw new OperacaoBancariaException("Cliente não encontrado");
        }

    }


    //MÉTODO DE DELETAR
    public void excluirCliente (Long id){

        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        Cliente cliente = clienteOptional.orElseThrow(() ->
                new OperacaoBancariaException("Cliente não existe"));

        clienteRepository.delete(cliente);

    }


    //MÉTODO PARA ATUALIZAR CLIENTE
    public Cliente clienteAtualizado (Long id, AtualizarCliente dto){

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() ->
                new OperacaoBancariaException("Cliente não exite"));


        Banco banco = bancoRepository.findById(dto.bancoId()).orElseThrow(() ->
                 new OperacaoBancariaException("Banco não encontrado"));


        cliente.setNome(dto.nome());
        cliente.setBanco(banco);

        return clienteRepository.save(cliente);

    }


    //ATUALIZAR CLIENTE UTILIZANDO "Optional" E "If"

//    public Cliente clienteAtualizado(Long id, AtualizarCliente dto) {
//        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
//
//        if (clienteOptional.isEmpty()) {
//            throw new OperacaoBancariaException("Cliente não existe com id: " + id);
//        }
//
//        Cliente cliente = clienteOptional.get();
//
//        Optional<Banco> bancoOptional = bancoRepository.findById(dto.bancoId());
//
//        if (bancoOptional.isEmpty()) {
//            throw new OperacaoBancariaException("Banco não encontrado com id: " + dto.bancoId());
//        }
//
//        Banco banco = bancoOptional.get();
//
//        cliente.setNome(dto.nome());
//        cliente.setBanco(banco);
//
//        return clienteRepository.save(cliente);
//    }
}
