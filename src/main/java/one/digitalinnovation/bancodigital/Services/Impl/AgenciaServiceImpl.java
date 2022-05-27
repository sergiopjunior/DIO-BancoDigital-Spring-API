package one.digitalinnovation.bancodigital.Services.Impl;

import one.digitalinnovation.bancodigital.Controllers.Exceptions.AgenciaException;
import one.digitalinnovation.bancodigital.Controllers.Exceptions.NumeroAgenciaInvalidoException;
import one.digitalinnovation.bancodigital.Models.Agencia;
import one.digitalinnovation.bancodigital.Models.AgenciaRepositorio;
import one.digitalinnovation.bancodigital.Services.AgenciaService;
import one.digitalinnovation.bancodigital.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgenciaServiceImpl implements AgenciaService {
    @Autowired
    private AgenciaRepositorio agenciaRepositorio;

    @Override
    public Iterable<Agencia> listarTodos() {
        return agenciaRepositorio.findAll();
    }

    @Override
    public Object buscarPorID(long ID) throws AgenciaException {
        Optional<Agencia> agencia = agenciaRepositorio.findById(ID);
        if (agencia.isEmpty())
            throw new AgenciaException(ID);

        return agencia.get();
    }

    @Override
    public Object inserir(Agencia agencia) throws Exception {
        return salvarAgencia(agencia);
    }

    @Override
    public Object atualizar(long ID, Agencia agencia) throws Exception{
        Optional<Agencia> agencia_obj = agenciaRepositorio.findById(ID);
        if (agencia_obj.isEmpty())
            throw new AgenciaException(ID);

        return salvarAgencia(agencia_obj.get().atualizar(agencia));
    }

    @Override
    public Object excluir(long ID) throws AgenciaException {
        Optional<Agencia> agencia = agenciaRepositorio.findById(ID);
        if (agencia.isEmpty())
            throw new AgenciaException(ID);

        agenciaRepositorio.deleteById(ID);
        return agencia.get();
    }

    private Object salvarAgencia(Agencia agencia) throws Exception{
        if (!Utils.validarAgenciaNumero(agencia.getNumero()))
            throw new NumeroAgenciaInvalidoException(agencia.getNumero());

        agenciaRepositorio.save(agencia);
        return agencia;
    }
}
