package one.digitalinnovation.bancodigital.Controllers.Exceptions;

public class NumeroAgenciaInvalidoException extends Exception{
    public NumeroAgenciaInvalidoException(String numero) {
        super(String.format("\"%s\" não é um número de agência válido.", numero));
    }
}