package br.alura.ONE_Challenge_LiterAlura.repository;

import br.alura.ONE_Challenge_LiterAlura.model.Autor;
import br.alura.ONE_Challenge_LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNome(String nome);

    @Query("SELECT l FROM Autor a JOIN a.livros l WHERE l.titulo = :titulo")
    Optional<Livro> findLivroByTitulo(String titulo);

    @Query("SELECT l FROM Autor a JOIN a.livros l")
    List<Livro> findAllLivro();

    @Query("SELECT a FROM Autor a WHERE :ano BETWEEN a.anoNascimento AND a.anoMorte")
    List<Autor> findAutorVivoEmAno(int ano);

    @Query("SELECT l FROM Autor a JOIN a.livros l WHERE l.idioma = :idioma")
    List<Livro> findLivroPorIdioma(String idioma);

    @Query("SELECT l FROM Autor a JOIN a.livros l ORDER BY l.nDownloads DESC LIMIT 5")
    List<Livro> find5LivrosMaisBaixados();
}
