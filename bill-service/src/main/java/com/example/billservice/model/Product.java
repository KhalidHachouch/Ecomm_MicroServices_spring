package com.example.billservice.model;

import lombok.Data;

@Data
public class Product {
    private long id;
    private double price;
    private String name;
    private int quantity;
}
