package dev.conta_bancaria.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "tb_banco")
public class Banco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    //1 BANCO -> N CLIENTES
    @OneToMany(mappedBy = "banco", cascade = CascadeType.ALL)
    private List<Cliente> listaClientes = new ArrayList<>();


    //CONSTRUTOR VAZIO
    public Banco() {
    }


    //CONSTRUTOR COM ARGUMENTOS - sem id
    public Banco(String nome) {
       this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    //FAZER COMPARAÇÃO ENTRE BANCOS ATRÁVEZ DO "ID"

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Banco banco = (Banco) o;
        return Objects.equals(id, banco.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
