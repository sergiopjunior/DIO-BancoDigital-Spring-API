package one.digitalinnovation.bancodigital.Models;

public interface IConta {
    void depositar(double valor) throws Exception;
    void sacar(int valor) throws Exception;
    void transferir(Conta conta, double valor) throws Exception;
}
