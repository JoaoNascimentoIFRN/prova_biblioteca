package br.edu.ifrn.prova1.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "livro")
@Table(name = "livro")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Livro {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;

    private String isbn;

    private int anoPublicacao;

    private int quantidadeCopiasDisponiveis;

    @ManyToMany
    @JoinTable(name = "livro_autor",joinColumns = @JoinColumn(name="livro_id"),
    inverseJoinColumns = @JoinColumn(name="autor_id"))
    private List<Autor> autores;

    @ManyToMany
    @JoinTable(name = "livro_emprestimo",joinColumns = @JoinColumn(name="livro_id"),
    inverseJoinColumns = @JoinColumn(name="emprestimo_id"))
    private List<Emprestimo> emprestimos;


}
