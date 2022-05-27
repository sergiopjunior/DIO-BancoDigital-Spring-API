package one.digitalinnovation.bancodigital.Models;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agencia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    private String numero;
    private String nome;

    public long getID(){
        return this.ID;
    }

    public String getNumero(){
        return this.numero;
    }

    protected void setNumero(String numero) {
        this.numero = numero;
    }
    public String getNome(){
        return this.nome;
    }

    protected void setNome(String nome){
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        else if (!(o instanceof Agencia)) {
            return false;
        }

        return this.numero.equals(((Agencia) o).getNumero())
                && this.nome.equals(((Agencia) o).getNome());
    }

    public Agencia atualizar(Agencia agencia) {
        setNumero(agencia.getNumero());
        setNome(agencia.getNome());

        return this;
    }
}
