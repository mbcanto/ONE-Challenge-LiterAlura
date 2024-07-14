package br.alura.ONE_Challenge_LiterAlura.model;

import jakarta.persistence.*;

@Entity
@Table (name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String idioma;
    private Integer nDownloads;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Autor autor;

    public Livro() {}

    public Livro(DadosLivroDTO dadosLivro){
        this.titulo = dadosLivro.titulo();
        this.idioma = dadosLivro.idiomas().get(0);
        this.nDownloads = dadosLivro.nDownloads();

    }

    @Override
    public String toString() {
        return "\nLivro: " + titulo +
                "\n------------------------- \nIdiomas:" + idioma +
                "\nAutor=" + autor.getNome() +
                "\nNúmero de Downloads=" + nDownloads +
                "\n-------------------------" ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getnDownloads() {
        return nDownloads;
    }

    public void setnDownloads(Integer nDownloads) {
        this.nDownloads = nDownloads;
    }
}

