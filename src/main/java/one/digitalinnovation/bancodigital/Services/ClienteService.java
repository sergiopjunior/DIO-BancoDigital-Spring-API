package one.digitalinnovation.bancodigital.Services;

import one.digitalinnovation.bancodigital.Controllers.Exceptions.ClienteException;
import one.digitalinnovation.bancodigital.Models.Cliente;
import org.springframework.http.ResponseEntity;

public interface ClienteService {
    Iterable<Cliente> listarTodos();
    Object buscarPorID(long ID) throws Exception;
    Object inserir(Cliente cliente) throws Exception;
    Object atualizar(long ID, Cliente cliente) throws Exception;
    Object excluir(long ID) throws Exception;
}
