package com.example.billservice.repository;

import com.example.billservice.entities.ProductItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductItemsRepository extends JpaRepository<ProductItems,Long> {
}
