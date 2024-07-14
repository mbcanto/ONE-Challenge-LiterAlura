package br.alura.ONE_Challenge_LiterAlura.model;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table (name = "autores")

public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Integer anoNascimento;
    private Integer anoMorte;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<Livro>();

    public Autor() {
    }


    public Autor(DadosAutorDTO dadosAutor) {
        this.nome = dadosAutor.nome();
        try {
            this.anoNascimento = Integer.parseInt(dadosAutor.anoNascimento());
        } catch (NumberFormatException e) {
            this.anoNascimento = 0;
        }
        try {
            this.anoMorte = Integer.parseInt(dadosAutor.anoMorte());
        } catch (NumberFormatException e) {
            this.anoMorte = 0;
        }
    }

    @Override
    public String toString() {
        return "Autor: " + nome +
                "\nAno de Nascimento: " + anoNascimento +
                "\nAno de Morte: " + anoMorte +
                "\nLivros Cadastrados: " + livros.stream()
                .map(l -> l.getTitulo())
                .collect(Collectors.toList());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(Integer anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public Integer getAnoMorte() {
        return anoMorte;
    }

    public void setAnoMorte(Integer anoMorte) {
        this.anoMorte = anoMorte;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        livros.forEach(l -> l.setAutor(this));
        this.livros = livros;
    }

    public void setLivro(Livro livro) {
        this.livros.add(livro);
        livro.setAutor(this);
    }
}

