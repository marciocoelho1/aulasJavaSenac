package br.com.loja.config;


import br.com.loja.model.Categoria;
import br.com.loja.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfiguracaoInicial {

    @Bean
    CommandLineRunner carregarCategorias(
            CategoriaRepository categoriaRepository){
          return args -> {
            if (categoriaRepository.count() == 0) {
                categoriaRepository.save(
                        new Categoria(
                                "Informática",
                                "Produtos de Informática"
                        )
                );
                categoriaRepository.save(
                        new Categoria(
                                "Escritório",
                                "Produtos de Escritório"
                        )
                );
            }
        };
    }
}
