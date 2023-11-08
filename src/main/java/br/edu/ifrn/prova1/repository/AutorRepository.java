package br.edu.ifrn.prova1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.prova1.domain.Autor;

public interface AutorRepository extends JpaRepository<Autor, Long>{
    
}
