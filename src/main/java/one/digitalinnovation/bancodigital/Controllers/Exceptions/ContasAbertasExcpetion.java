package one.digitalinnovation.bancodigital.Controllers.Exceptions;

import one.digitalinnovation.bancodigital.Models.Conta;

import java.util.List;
import java.util.stream.Collectors;

public class ContasAbertasExcpetion extends Exception{
    public ContasAbertasExcpetion(List<Conta> contas) {
        super(makeString(contas));
    }

    private static String makeString(List<Conta> contas) {
        String head = String.format("O Cliente possui \"%d\" contas abertas, feche-as para concluir a exclusão%n.\nContas abertas:", contas.size());

        return contas.stream().map(String::valueOf).collect(Collectors.joining("\n", head, ""));
    }
}
