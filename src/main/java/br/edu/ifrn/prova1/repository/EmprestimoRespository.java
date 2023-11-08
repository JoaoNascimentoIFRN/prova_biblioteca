package br.edu.ifrn.prova1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifrn.prova1.domain.Emprestimo;

public interface EmprestimoRespository extends JpaRepository<Emprestimo, Long>{
    
}
