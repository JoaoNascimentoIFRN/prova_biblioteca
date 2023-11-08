package br.edu.ifrn.prova1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ifrn.prova1.domain.Autor;
import br.edu.ifrn.prova1.repository.AutorRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("autores")
public class AutorController {
    
    
    @Autowired
    private AutorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Autor autor,
            UriComponentsBuilder uriBuilder) {
        Autor autorLocal = repository.save(autor);
        var uri = uriBuilder.path("/autores/{id}").buildAndExpand(autorLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(repository.getReferenceById(id));
    }

    @GetMapping
    public ResponseEntity<List<Autor>> listar() {    
        return ResponseEntity.ok(repository.findAll());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> excluir(@PathVariable Long id) {        
        repository.delete(repository.getReferenceById(id));
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Autor> atualizar(@RequestBody @Valid Autor autor) {
        Autor autorLocal = repository.findById(autor.getId()).get();
        autorLocal.setNome(autor.getNome());
        return ResponseEntity.ok(autorLocal);
    }
}
