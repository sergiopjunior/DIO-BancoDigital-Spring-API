package one.digitalinnovation.bancodigital.Models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;

@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String nome;
    private String cpf;
    private int idade;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Endereco endereco;

    public long getID() {
        return this.ID;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return this.cpf;
    }

    public int getIdade() {
        return this.idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Cliente atualizar(Cliente cliente) {
        setNome(cliente.getNome());
        setEndereco(cliente.getEndereco());
        setIdade(cliente.getIdade());

        return this;
    }
}
