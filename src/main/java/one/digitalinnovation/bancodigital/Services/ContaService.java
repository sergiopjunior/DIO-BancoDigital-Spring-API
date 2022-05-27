package one.digitalinnovation.bancodigital.Services;

import one.digitalinnovation.bancodigital.Models.Conta;
import org.springframework.http.ResponseEntity;

public interface ContaService {
    Iterable<Conta> listarTodos();
    Object buscarPorID(long ID) throws Exception;
    Object inserir(long agenciaID, long clienteID) throws Exception;
    Object atualizar(long ID, Conta conta) throws Exception;
    Object excluir(long ID) throws Exception
}
