package dev.conta_bancaria.controller;

import dev.conta_bancaria.dto.AtualizarCliente;
import dev.conta_bancaria.dto.ClienteDto;
import dev.conta_bancaria.entities.Cliente;
import dev.conta_bancaria.service.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/clientes")
public class ClienteController {


    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDto> criar(@RequestBody ClienteDto clienteDto) {

        Cliente cliente = clienteService.cliente(clienteDto);

        ClienteDto clienteResposta = new ClienteDto(cliente.getId(), cliente.getNome(), cliente.getBanco().getId());

        return ResponseEntity.ok(clienteResposta);

    }


    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {

        List<Cliente> clientes = clienteService.listarClientes();

        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {

        if (id == null || id < 0) {
            return ResponseEntity.badRequest().body("Id invalido");
        }

        Cliente clienteId = clienteService.clienteId(id);

        return ResponseEntity.ok(clienteId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {

        if (id == null || id < 0) {
            return ResponseEntity.badRequest().build();
        }
        clienteService.excluirCliente(id);

       return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")

    public ResponseEntity<Cliente> atualizacao(@PathVariable Long id, @RequestBody AtualizarCliente atualizarCliente){

        if (id == null || id < 0) {
            return ResponseEntity.badRequest().build();
        }

        Cliente clienteAtualizado = clienteService.clienteAtualizado(id,atualizarCliente);

        return ResponseEntity.ok(clienteAtualizado);
    }

}




