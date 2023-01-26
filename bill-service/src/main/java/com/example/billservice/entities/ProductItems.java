package com.example.billservice.entities;

import com.example.billservice.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long productId;
    @ManyToOne
    @JsonIgnore
    private Bill bill;
    private int quantity;
    private double price;
    private double discount;
    @Transient
    private Product product;

}
