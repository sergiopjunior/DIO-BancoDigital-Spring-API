package one.digitalinnovation.bancodigital.Services.Impl;

import one.digitalinnovation.bancodigital.Controllers.Exceptions.AgenciaException;
import one.digitalinnovation.bancodigital.Controllers.Exceptions.ClienteException;
import one.digitalinnovation.bancodigital.Controllers.Exceptions.ContaException;
import one.digitalinnovation.bancodigital.Models.*;
import one.digitalinnovation.bancodigital.Services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContaServiceImpl implements ContaService {
    @Autowired
    private AgenciaRepositorio agenciaRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private ContaRepositorio contaRepositorio;


    @Override
    public Iterable<Conta> listarTodos() {
        return contaRepositorio.findAll();
    }

    @Override
    public Object buscarPorID(long ID) throws Exception{
        Optional<Conta> conta = contaRepositorio.findById(ID);
        if (conta.isEmpty())
            throw new ContaException(ID);

        return conta.get();
    }

    @Override
    public Object inserir(long agenciaID, long clienteID) throws Exception{
        return salvarConta(agenciaID, clienteID, new Conta());
    }

    @Override
    public Object atualizar(long contaID, Conta conta) throws Exception{
        Optional<Conta> conta_obj = contaRepositorio.findById(contaID);
        if (conta_obj.isEmpty())
            throw new ContaException(contaID);

        return salvarConta(conta_obj.get().getAgencia().getID(), conta_obj.get().getCliente().getID(), conta_obj.get().atualizar(conta));
    }

    @Override
    public Object excluir(long ID) throws Exception{
        Optional<Conta> conta = contaRepositorio.findById(ID);
        if (conta.isEmpty())
            throw new ContaException(ID);

        clienteRepositorio.deleteById(ID);
        return conta.get();
    }

    private Object salvarConta(long agenciaID, long clienteID, Conta conta) throws Exception{
        Optional<Agencia> agencia = agenciaRepositorio.findById(agenciaID);
        Optional<Cliente> cliente = clienteRepositorio.findById(clienteID);

        if (agencia.isEmpty())
            throw new AgenciaException(agenciaID);
        if (cliente.isEmpty())
            throw new ClienteException(clienteID);

        conta.setNumero();
        conta.setAgencia(agencia.get());
        conta.setCliente(cliente.get());
        contaRepositorio.save(conta);
        return conta;
    }
}
