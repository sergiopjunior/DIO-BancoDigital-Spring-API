package one.digitalinnovation.bancodigital.Controllers.Exceptions;

public class SaldoInsuficienteException extends Exception {
    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }
}
