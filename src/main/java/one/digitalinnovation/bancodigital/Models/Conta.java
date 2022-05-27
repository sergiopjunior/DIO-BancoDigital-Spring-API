package one.digitalinnovation.bancodigital.Models;

import one.digitalinnovation.bancodigital.Controllers.Exceptions.OperacaoBloqueadaException;
import one.digitalinnovation.bancodigital.Controllers.Exceptions.SaldoInsuficienteException;
import one.digitalinnovation.bancodigital.Controllers.Exceptions.ValorLimiteAtingidoException;
import one.digitalinnovation.bancodigital.Controllers.Exceptions.ValorZeradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.*;

@Entity
public class Conta implements IConta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ID;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Cliente cliente;
    private String numero;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Agencia agencia;
    private String tipo_conta;
    private double saldo;
    private double taxa_transferencia;
    private double taxa_saque;
    private double deposito_limite;
    private double saque_limite;
    private double transferencia_limite;

    public void Setup() {
        if (this.tipo_conta.equalsIgnoreCase("Corrente"))
        {
            this.deposito_limite = 1500;
            this.saque_limite = 2000;
            this.transferencia_limite = 1000;
            this.taxa_transferencia = 2;
            this.taxa_saque = 0;
        }
        else {
            this.deposito_limite = 800;
            this.saque_limite = 1000;
            this.transferencia_limite = 500;
            this.taxa_transferencia = 0.7;
            this.taxa_saque = 5;
        }
        setNumero();
    }

    public long getID() {
        return ID;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public String getNumero() {
        return this.numero;
    }

    public void setNumero() {
        this.numero = String.valueOf(this.hashCode());
    }

    public Agencia getAgencia() {
        return this.agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public String getTipoConta() {
        return this.tipo_conta;
    }

    public void setTipoConta(String tipo) {
        this.tipo_conta = tipo;
    }

    protected double getSaldo() {
        return this.saldo;
    }

    protected void setSaldo(double valor) {
        this.saldo += valor;
    }

    public double getTaxaTransferencia() {
        return this.taxa_transferencia;
    }

    public double getDepositoLimite() {
        return deposito_limite;
    }

    public double getSaqueLimite() {
        return saque_limite;
    }

    public double getTransferenciaLimite() {
        return transferencia_limite;
    }

    @Override
    public void depositar(double valor) throws Exception {
        if (valor > this.deposito_limite)
            throw new ValorLimiteAtingidoException(String.format("O valor R$%.2f supera o limite de R$%.2f para depósitos.\n",
                    valor, this.deposito_limite));

        this.setSaldo(valor);
    }

    @Override
    public void transferir(Conta conta, double valor) throws Exception {
        if (valor > this.transferencia_limite)
            throw new ValorLimiteAtingidoException(String.format("O valor R$%.2f supera o limite de R$%.2f para transferências.\n",
                    valor, this.transferencia_limite));
        else if (this.saldo - valor - this.taxa_transferencia < 0)
            throw new SaldoInsuficienteException(String.format("O valor da transferência deve ser maior do que o " +
                    "saldo da conta somado à taxa de R$%.2f.\n", this.taxa_transferencia));
        if (this.agencia.getID() != conta.getAgencia().getID())
            throw new OperacaoBloqueadaException("Não é possível realizar transferências entre contas de diferentes agências.\n");

        this.setSaldo(-(valor + this.taxa_transferencia));

    }

    @Override
    public void sacar(int valor) throws Exception {
        if (valor > this.saque_limite)
            throw new ValorLimiteAtingidoException(String.format("O valor R$%d supera o limite de R$%.2f para saques.\n",
                    valor, this.saque_limite));
        else if (valor <= 0)
            throw new ValorZeradoException("O valor da operação deve ser maior que R$0,00.\n");
        else if (this.saldo - valor - this.taxa_saque < 0) {
            String msg = this.taxa_saque > 0 ? String.format(" somado à taxa de %.2f", this.taxa_saque) : "";
            throw new SaldoInsuficienteException(String.format("Erro ao efetuar saque. " +
                    "O valor do saque deve ser maior do que o saldo da conta%s.\n", msg));
        }
        this.setSaldo(-(valor - this.taxa_saque));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        else if (!(o instanceof Conta)) {
            return false;
        }

        return this.numero.equals(((Conta) o).getNumero())
                && this.agencia.getID() == ((Conta) o).getAgencia().getID();
    }

    public Conta atualizar(Conta conta) {
        setTipoConta(conta.getTipoConta());
        Setup();

        return this;
    }

    @Override
    public int hashCode() {
        return Math.abs((int) (this.tipo_conta.hashCode() + this.agencia.getID() + this.cliente.getID() + this.ID));
    }

    @Override
    public String toString() {
        return String.format("Nº: %s - Agência: %s - Agência Nº: %s - Titular: %s - Titular CPF: %s - Tipo: %s - Saldo: R$%.2f",
                this.numero, this.agencia.getNome(), this.agencia.getNumero(),
                this.cliente.getNome(), this.cliente.getCPF(), this.tipo_conta, this.saldo);
    }
}
