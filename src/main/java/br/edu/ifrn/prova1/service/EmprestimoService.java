package br.edu.ifrn.prova1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifrn.prova1.domain.Emprestimo;
import br.edu.ifrn.prova1.repository.UsuarioRepository;


@Service
public class EmprestimoService {

    private static final int NUMERO_MAXIMO_EMPRESTIMO = 5;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean isPossivelEmprestimo(Long idUsuario){            
        List<Emprestimo> emprestimosEmAberto = usuarioRepository.listEmprestimosEmAberto(idUsuario);
        int contadorLivros = 0;
        for (Emprestimo emprestimo : emprestimosEmAberto){
            contadorLivros = contadorLivros + emprestimo.getLivros().size();
        }

        if (contadorLivros >= NUMERO_MAXIMO_EMPRESTIMO)       
            return false;
        else
            return true;
    }

}
