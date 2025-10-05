package dev.conta_bancaria.entities;

import dev.conta_bancaria.exception.OperacaoBancariaException;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name ="tb_conta_bancaria")
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double saldo;

    private double limiteChequeEspecial;

    //MUITAS CONTAS PERTENCEM A UM CLIENTE
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    //CONSTRUTOR VAZIO
    public ContaBancaria() {
    }

    //CONSTRUTOR COM ARGUMENTOS, QUANDO INSTANCIAR A CLASSE VOU PASSAR O CLIENTE, SALDO E LIMITE DE SAQUE

    public ContaBancaria(Long id, double saldo, double limiteChequeEspecial, Cliente cliente) {
        this.id = id;
        this.saldo = saldo;
        this.limiteChequeEspecial = limiteChequeEspecial;
        this.cliente = cliente;
    }

    //MÉTODO PARA SACAR
    public double sacar(double valor){

        if (valor <= 0){
            throw new OperacaoBancariaException("O valor do saque deve ser maior que ZERO");
        }

        double saldoDisponivel = saldo + limiteChequeEspecial;

        if (valor > saldoDisponivel){
            throw new OperacaoBancariaException("Saldo insuficiente (incluindo com cgeque espécial)");

        }

        saldo -= valor;
        return saldo;
    }

    //MÉTODO PARA DEPOSITAR
    public double depositar(double valor){

        if (valor <= 0){
        }

        saldo += valor;
        return saldo;

    }

    //GETTER E SETTER


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getLimiteChequeEspecial() {
        return limiteChequeEspecial;
    }

    public void setLimiteChequeEspecial(double limiteChequeEspecial) {
        this.limiteChequeEspecial = limiteChequeEspecial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    //COMPARAR UMA CONTA COM OUTRA, ATRÁVES DO ID
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContaBancaria that = (ContaBancaria) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
