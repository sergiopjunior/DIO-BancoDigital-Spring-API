package one.digitalinnovation.bancodigital.Services;

import one.digitalinnovation.bancodigital.Models.Agencia;
import org.springframework.http.ResponseEntity;

public interface AgenciaService {
    Iterable<Agencia> listarTodos();
    Object buscarPorID(long ID) throws Exception;
    Object inserir(Agencia agencia) throws Exception;
    Object atualizar(long ID, Agencia agencia) throws Exception;
    Object excluir(long ID) throws Exception;
}
