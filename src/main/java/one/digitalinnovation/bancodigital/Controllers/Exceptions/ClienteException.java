package one.digitalinnovation.bancodigital.Controllers.Exceptions;

public class ClienteException extends Exception{
    public ClienteException(long ID) {
        super(String.format("Cliente com ID \"%d\" não encontrado.", ID));
    }
}
