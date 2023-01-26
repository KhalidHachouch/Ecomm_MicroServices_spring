package com.example.productservice;

import com.example.productservice.entities.Product;
import com.example.productservice.entities.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(ProductRepository productRepository, RepositoryRestConfiguration repositoryRestConfiguration){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Product.class);
            productRepository.saveAll(
                    List.of(
                            Product.builder().name("laptop").quantity(20).price(2000).build(),
                            Product.builder().name("mobile").quantity(15).price(1500).build()
                    )
            );

            productRepository.findAll().forEach(System.out::println);
        };
    }

}
