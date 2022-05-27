package one.digitalinnovation.bancodigital.Models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepositorio extends CrudRepository<Endereco, String> {
}
