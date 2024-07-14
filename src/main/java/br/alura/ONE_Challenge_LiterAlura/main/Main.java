package br.alura.ONE_Challenge_LiterAlura.main;

import br.alura.ONE_Challenge_LiterAlura.model.*;
import br.alura.ONE_Challenge_LiterAlura.repository.AutorRepository;
import br.alura.ONE_Challenge_LiterAlura.service.ConsumoApi;
import br.alura.ONE_Challenge_LiterAlura.service.ConverteDados;


import java.time.Year;
import java.util.*;


public class Main {

    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books/?search=";
    private Scanner leitura = new Scanner(System.in);
    private List<Livro> listaLivros = new ArrayList<>();
    private List<Autor> listaAutores = new ArrayList<>();
    private List<String> listaIdiomas = new ArrayList<>();
    final Year anoAtual = Year.now();

    private final AutorRepository repositorioAutor;



    private Livro livroNovo;

    public Main (AutorRepository repositorioAutor){
        this.repositorioAutor = repositorioAutor;
    }


    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    1 - Buscar livro
                    2 - Listar livros
                    3 - Listar autores
                    4 - Listar autores vivos em determinado ano
                    5 - Listar livros por idioma
                    6 - Listar livros mais baixados
                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    getDadosLivro();
                    break;
                case 2:
                    listarLivrosBuscados();
                    break;
                case 3:
                    listarAutoresBuscados();
                    break;
                case 4:
                    listarAutoresPorAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 6:
                    listar5LivrosMaisBaixados();
                    break;




                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void listar5LivrosMaisBaixados() {
        System.out.println("Aqui estão os 5 livros mais baixados");
        listaLivros = repositorioAutor.find5LivrosMaisBaixados();
        System.out.println(listaLivros);
    }

    private void listarLivrosPorIdioma() {
        System.out.println("Escolher idioma para consulta:");
        for (Livro livro : repositorioAutor.findAllLivro()) {
//            System.out.println();
            listaIdiomas.add(livro.getIdioma());
//            System.out.println(listaIdiomas);
        }
        listaIdiomas.stream().distinct().forEach(System.out::println);
        var idioma = leitura.nextLine();
        if (listaIdiomas.contains(idioma)) {
            listaLivros = repositorioAutor.findLivroPorIdioma(idioma);
            System.out.println("Encontrados " + listaIdiomas.size() + " livros neste idioma.");
            System.out.println(listaLivros);

        } else {
            System.out.println("Idioma não reconhecido.");
        }
    }

    private void listarAutoresPorAno() {
        System.out.println("Digite ano de consulta para autores");
        var ano = leitura.nextInt();
        leitura.nextLine();
        if (ano > Integer.parseInt(anoAtual.toString())) {
            System.out.println("Ano futuro, análise não é possível.");
        } else {
            listaAutores = repositorioAutor.findAutorVivoEmAno(ano);
            if (listaAutores.isEmpty()) {
                System.out.println("Nenhum autor cadastrado estava vivo no ano " + ano);
            } else {
                listaAutores.stream()
                        .sorted(Comparator.comparing((Autor::getNome)))
                        .forEach(System.out::println);

            }
        }
    }

    private void listarAutoresBuscados() {
       listaAutores = repositorioAutor.findAll();
       listaAutores.stream()
               .sorted(Comparator.comparing((Autor::getNome)))
               .forEach(System.out::println);
    }

    private void getDadosLivro() {
        System.out.println("Digite o nome do livro para busca");
        var nomeLivro = leitura.nextLine();
//        System.out.println(ENDERECO + nomeLivro.toLowerCase().replace(" ", "%20"));
        var json = consumo.obterDados(ENDERECO + nomeLivro.toLowerCase().replace(" ", "%20"));
//        System.out.println(json);
        var dados = conversor.obterDados(json, DadosSearchDTO.class);
        System.out.println(dados);
        if (dados.count() == 0) {
            System.out.println("Livro não encontrado");
        } else {
            var livroBusca=dados.results().get(0);
//            System.out.println(livroBusca);
            var autorBusca = livroBusca.dadosAutorDTO().get(0);
//            System.out.println(autorBusca);

            var autorRepositorio = repositorioAutor.findByNome(autorBusca.nome());
            var livroRepositorio = repositorioAutor.findLivroByTitulo(livroBusca.titulo());

            if  (autorRepositorio.isPresent()) {
                System.out.println("Autor existente!");
                System.out.println(autorRepositorio.get());

                if (livroRepositorio.isPresent()) {
                    System.out.println("Livro já cadastrado.");
                    System.out.println(livroRepositorio.get());
                } else {
                    System.out.println("Novo livro, será cadastrado.");
                    Livro livro = new Livro(livroBusca);
                    autorRepositorio.get().setLivro(livro);
                    repositorioAutor.save(autorRepositorio.get());
                        }
                    }
             else {
                System.out.println("Novo autor e novo livro, serão cadastrados.");
                Autor autor = new Autor(autorBusca);
                Livro livro = new Livro(livroBusca);
                autor.setLivro(livro);
                repositorioAutor.save(autor);
            }
        }
    }

    private void listarLivrosBuscados() {
        listaLivros = repositorioAutor.findAllLivro();
        System.out.println(listaLivros);
    }



}

