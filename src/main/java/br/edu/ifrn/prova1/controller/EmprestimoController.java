package br.edu.ifrn.prova1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifrn.prova1.domain.Emprestimo;
import br.edu.ifrn.prova1.repository.EmprestimoRespository;
import br.edu.ifrn.prova1.service.EmprestimoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("emprestimos")
public class EmprestimoController {
    
    
    @Autowired
    private EmprestimoRespository repository;

    @Autowired
    private EmprestimoService emprestimoService;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Emprestimo emprestimo,
            UriComponentsBuilder uriBuilder) throws Exception {
        if (!emprestimoService.isPossivelEmprestimo(emprestimo.getUsuario().getId()))
            throw new Exception("Usuário com limite de livros alcançados");
        Emprestimo emprestimoLocal = repository.save(emprestimo);
        var uri = uriBuilder.path("/emprestimos/{id}").buildAndExpand(emprestimoLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emprestimo> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(repository.getReferenceById(id));
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> listar() {    
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {        
        repository.delete(repository.getReferenceById(id));
        return ResponseEntity.noContent().build();
    }

    /*@PutMapping
    @Transactional
    public ResponseEntity<Emprestimo> atualizar(@RequestBody @Valid Emprestimo emprestimo) {
        //Emprestimo emprestimoLocal = repository.findById(emprestimo.getId()).get();
        //emprestimoLocal.setNome(emprestimo.get());
        return ResponseEntity.ok(emprestimoLocal);
    }*/
}
