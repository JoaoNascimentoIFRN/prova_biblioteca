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

import br.edu.ifrn.prova1.domain.Livro;
import br.edu.ifrn.prova1.repository.LivroRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("livros")
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Livro livro,
            UriComponentsBuilder uriBuilder) {
        Livro livroLocal = repository.save(livro);
        var uri = uriBuilder.path("/livros/{id}").buildAndExpand(livroLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(repository.getReferenceById(id));
    }

    @GetMapping
    public ResponseEntity<List<Livro>> listar() {    
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
    public ResponseEntity<Livro> atualizar(@RequestBody @Valid Livro livro) {
        Livro livroLocal = repository.findById(livro.getId()).get();
        livroLocal.setTitulo(livro.getTitulo());
        livroLocal.setIsbn(livro.getIsbn());
        livroLocal.setQuantidadeCopiasDisponiveis(livro.getQuantidadeCopiasDisponiveis());
        return ResponseEntity.ok(livroLocal);
    }

}
