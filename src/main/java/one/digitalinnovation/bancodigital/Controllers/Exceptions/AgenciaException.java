package one.digitalinnovation.bancodigital.Controllers.Exceptions;

public class AgenciaException extends Exception{
    public AgenciaException(long ID) {
        super(String.format("Agência com ID \"%d\" não encontrada.", ID));
    }
}
