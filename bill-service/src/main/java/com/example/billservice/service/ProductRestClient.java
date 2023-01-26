package com.example.billservice.service;

import com.example.billservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PRODUCT-SERVICE")
public interface ProductRestClient {
    @GetMapping(path = "/products/{id}")
    public Product findProductById(@PathVariable long id);
    @GetMapping("/products")
    PagedModel<Product>allProducts();
}
