package br.edu.ifrn.prova1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ifrn.prova1.domain.Emprestimo;
import br.edu.ifrn.prova1.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
   
   @Query(value = "select * from emprestimo where usuario_id = ?1 and data_devolucao = null;", nativeQuery = true) 
   List<Emprestimo> listEmprestimosEmAberto(Long idUsuario); 
}
