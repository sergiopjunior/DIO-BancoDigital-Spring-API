package one.digitalinnovation.bancodigital.Controllers.Exceptions;

public class ContaException extends Exception{
    public ContaException(long ID) {
        super(String.format("Conta com ID \"%d\" não encontrada.", ID));
    }
}
