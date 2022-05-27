package one.digitalinnovation.bancodigital.Controllers.Exceptions;

public class CpfInvalidoException extends Exception {
    public CpfInvalidoException(String cpf) {
        super(String.format("CPF \"%s\" inválido.", cpf));
    }
}
