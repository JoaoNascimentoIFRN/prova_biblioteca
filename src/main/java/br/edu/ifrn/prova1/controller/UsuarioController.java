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

import br.edu.ifrn.prova1.domain.Usuario;
import br.edu.ifrn.prova1.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
    

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<Object> cadastrar(@RequestBody @Valid Usuario usuario,
            UriComponentsBuilder uriBuilder) {
        Usuario usuarioLocal = repository.save(usuario);
        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioLocal.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(repository.getReferenceById(id));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {    
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
    public ResponseEntity<Usuario> atualizar(@RequestBody @Valid Usuario usuario) {
        Usuario usuarioLocal = repository.findById(usuario.getId()).get();
        usuarioLocal.setNome(usuario.getNome());
        return ResponseEntity.ok(usuarioLocal);
    }

}
