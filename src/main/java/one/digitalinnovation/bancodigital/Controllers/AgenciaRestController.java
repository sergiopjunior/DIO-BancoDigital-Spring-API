package one.digitalinnovation.bancodigital.Controllers;

import one.digitalinnovation.bancodigital.Models.Agencia;
import one.digitalinnovation.bancodigital.Services.AgenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("agencias")
public class AgenciaRestController {
    @Autowired
    private AgenciaService agenciaService;

    @GetMapping
    public ResponseEntity<Iterable<Agencia>> buscarTodos() {
        return ResponseEntity.ok(agenciaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable long id) {
        try {
            return new ResponseEntity<>(agenciaService.buscarPorID(id), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody Agencia agencia) {
        try {
            return new ResponseEntity<>(agenciaService.inserir(agencia), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable long id, @RequestBody Agencia agencia) {
        try {
            return new ResponseEntity<>(agenciaService.atualizar(id, agencia), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    // Desativado por regra de negócio
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> excluir(@PathVariable long id) {
//        try {
//            return new ResponseEntity<>(agenciaService.excluir(id), HttpStatus.OK);
//        }
//        catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
//        }
//    }
}
