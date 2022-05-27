package one.digitalinnovation.bancodigital.Controllers.Exceptions;

public class CepInvalidoException extends Exception {
    public CepInvalidoException(String cep) {
        super(String.format("CEP \"%s\" inválido.", cep));
    }
}
