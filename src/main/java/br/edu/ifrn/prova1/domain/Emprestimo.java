package br.edu.ifrn.prova1.domain;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "emprestimo")
@Table(name = "emprestimo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Emprestimo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    private Long id;

    private LocalDateTime dataEmprestimo;

    private LocalDateTime dataDevolucao;

    @ManyToMany(mappedBy = "emprestimos", fetch = FetchType.EAGER)
    private List<Livro> livros;
    //Set

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
