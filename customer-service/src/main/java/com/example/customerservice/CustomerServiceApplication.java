package com.example.customerservice;

import com.example.customerservice.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


import java.util.List;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }
    @Bean
    public CommandLineRunner commandLineRunner(RepositoryRestConfiguration repositoryRestConfiguration, CustomerRepository customerRepository){
        return args -> {
            repositoryRestConfiguration.exposeIdsFor(Customer.class);
            customerRepository.saveAll(
                    List.of(
                            Customer.builder().name("khalid").email("khalid@gmail.com").build(),
                            Customer.builder().name("test").email("test@gmail.com").build()
                    )
            );

            customerRepository.findAll().forEach(System.out::println);
        };
    }

}
