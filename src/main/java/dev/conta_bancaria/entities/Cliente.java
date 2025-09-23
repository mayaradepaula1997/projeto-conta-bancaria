package dev.conta_bancaria.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    //MUITOS CLIENTES PERTENCEM A UM BANCO
    @ManyToOne
    @JoinColumn(name = "banco_id")
    private Banco banco;

    //1 CLIENTE -> N CONTAS)
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private ContaBancaria contaBancaria;


    //CONSTRUTOR VAZIO
    public Cliente() {
    }

    //CONSTRUTOR COM ARGUMENTOS - apenas o nome e banco
    public Cliente(String nome, Banco banco) {
        this.nome = nome;
        this.banco = banco;
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

    public Banco getBanco() {
        return banco;
    }

    public void setBanco(Banco banco) {
        this.banco = banco;
    }

    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(ContaBancaria contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    //COMPARANDO O CLIENTE ATRÁVEZ DO ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
