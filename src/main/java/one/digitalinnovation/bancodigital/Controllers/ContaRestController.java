package one.digitalinnovation.bancodigital.Controllers;

import one.digitalinnovation.bancodigital.Models.Cliente;
import one.digitalinnovation.bancodigital.Models.Conta;
import one.digitalinnovation.bancodigital.Services.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contas")
public class ContaRestController {
    @Autowired
    private ContaService contaService;

    @GetMapping
    public ResponseEntity<Iterable<Conta>> buscarTodos() {
        return ResponseEntity.ok(contaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable long id) {
        try {
            return new ResponseEntity<>(contaService.buscarPorID(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/{agenciaID}/{clienteID}/{tipo_conta}")
    public ResponseEntity<Object> inserir(@PathVariable long agenciaID, @PathVariable long clienteID, @PathVariable String tipo_conta) {
        try {
            return new ResponseEntity<>(contaService.inserir(agenciaID, clienteID, tipo_conta), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{contaID}")
    public ResponseEntity<Object> atualizar(@PathVariable long contaID, @RequestBody Conta conta) {
        try {
            return new ResponseEntity<>(contaService.atualizar(contaID, conta), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> excluir(@PathVariable long id) {
        try {
            return new ResponseEntity<>(contaService.excluir(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
