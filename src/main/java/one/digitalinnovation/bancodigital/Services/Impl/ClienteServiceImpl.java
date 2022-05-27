package one.digitalinnovation.bancodigital.Services.Impl;

import one.digitalinnovation.bancodigital.Controllers.Exceptions.CepInvalidoException;
import one.digitalinnovation.bancodigital.Controllers.Exceptions.ClienteException;
import one.digitalinnovation.bancodigital.Controllers.Exceptions.CpfInvalidoException;
import one.digitalinnovation.bancodigital.Models.Cliente;
import one.digitalinnovation.bancodigital.Models.ClienteRepositorio;
import one.digitalinnovation.bancodigital.Models.Endereco;
import one.digitalinnovation.bancodigital.Models.EnderecoRepositorio;
import one.digitalinnovation.bancodigital.Services.ClienteService;
import one.digitalinnovation.bancodigital.Services.ViaCepService;
import one.digitalinnovation.bancodigital.Utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    @Autowired
    private EnderecoRepositorio enderecoRepositorio;
    @Autowired
    private ViaCepService viaCepService;

    private Endereco findEnderecoByCep(String cep) {
        Iterable<Endereco> enderecos = enderecoRepositorio.findAll();
        for (Endereco e: enderecos){
            if (e.getCep().equalsIgnoreCase(cep))
                return e;
        }
        return null;
    }

    @Override
    public Iterable<Cliente> listarTodos() {
        return clienteRepositorio.findAll();
    }

    @Override
    public Object buscarPorID(long ID) throws Exception{
        Optional<Cliente> cliente = clienteRepositorio.findById(ID);
        if (cliente.isEmpty())
            throw new ClienteException(ID);

        return cliente.get();
    }

    @Override
    public Object inserir(Cliente cliente) throws Exception{
        return salvarCliente(cliente);
    }

    @Override
    public Object atualizar(long ID, Cliente cliente) throws Exception {
        Optional<Cliente> cliente_obj = clienteRepositorio.findById(ID);
        if (cliente_obj.isEmpty())
            throw new ClienteException(ID);

        return salvarCliente(cliente_obj.get().atualizar(cliente));
    }

    @Override
    public Object excluir(long ID) throws Exception {
        Optional<Cliente> cliente = clienteRepositorio.findById(ID);
        if (cliente.isEmpty())
            throw new ClienteException(ID);

        clienteRepositorio.deleteById(ID);
        return cliente.get();
    }

    private Object salvarCliente(Cliente cliente) throws Exception {
       if (!Utils.validarCPF(cliente.getCPF()))
           throw new CpfInvalidoException(cliente.getCPF());

        String cep = cliente.getEndereco().getCep();

        Endereco endereco = findEnderecoByCep(cep);
        if (endereco == null) {
            try {
                    endereco = viaCepService.consultarCep(cep);
                    endereco.setCep(endereco.getCep().replace("-", ""));
            } catch (Exception e) {
                    throw new CepInvalidoException(cep);
            }
        }

        cliente.setEndereco(endereco); // CascadeType.PERSIST salva o Cliente persistindo o Endereço
        clienteRepositorio.save(cliente);
        return cliente;
    }
}