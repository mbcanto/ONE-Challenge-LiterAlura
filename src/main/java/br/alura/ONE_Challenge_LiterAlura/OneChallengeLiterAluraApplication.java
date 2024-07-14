package br.alura.ONE_Challenge_LiterAlura;

import br.alura.ONE_Challenge_LiterAlura.main.Main;
import br.alura.ONE_Challenge_LiterAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OneChallengeLiterAluraApplication implements CommandLineRunner {

	@Autowired
	private AutorRepository repositorioAutor;


	public static void main(String[] args) {
		SpringApplication.run(OneChallengeLiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Main main = new Main(repositorioAutor);
		main.exibeMenu();
	}
}
