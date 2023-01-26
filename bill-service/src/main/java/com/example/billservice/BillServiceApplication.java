package com.example.billservice;

import com.example.billservice.entities.Bill;
import com.example.billservice.entities.ProductItems;
import com.example.billservice.model.Customer;
import com.example.billservice.model.Product;
import com.example.billservice.repository.BillRepository;
import com.example.billservice.repository.ProductItemsRepository;
import com.example.billservice.service.CustomerRestClient;
import com.example.billservice.service.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Collection;
import java.util.Date;
import java.util.Random;

@SpringBootApplication
@EnableFeignClients
public class BillServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillServiceApplication.class, args);
    }
    @Bean
    CommandLineRunner commandLineRunner(BillRepository billRepository, ProductItemsRepository productItemsRepository,
                                        CustomerRestClient customerRestClient, ProductRestClient productRestClient){
        return args -> {
            Collection<Product> products=productRestClient.allProducts().getContent();
            long customerId = 1L;
            Customer customer=customerRestClient.findCustomerById(customerId);
            if(customer==null) throw new RuntimeException("customer not found");
            Bill bill =Bill.builder().billDate(new Date()).customerId(customerId).build();
            Bill savedBill=billRepository.save(bill);

            products.forEach(p ->{
                ProductItems productItem = new ProductItems();
                productItem.setBill(savedBill);
                productItem.setPrice(p.getPrice());
                productItem.setProductId(p.getId());
                productItem.setQuantity(1 + new Random().nextInt(10));
                productItem.setDiscount(Math.random());
                productItemsRepository.save(productItem);
            });
        };
    }

}
