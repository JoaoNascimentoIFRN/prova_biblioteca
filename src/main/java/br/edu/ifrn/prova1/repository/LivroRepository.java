package br.edu.ifrn.prova1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.prova1.domain.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{
    
}
