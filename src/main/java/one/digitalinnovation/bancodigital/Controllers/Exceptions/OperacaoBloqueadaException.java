package one.digitalinnovation.bancodigital.Controllers.Exceptions;

public class OperacaoBloqueadaException extends Exception {
    public OperacaoBloqueadaException(String mensagem) {
        super(mensagem);
    }
}
