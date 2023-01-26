package com.example.billservice.controller;

import com.example.billservice.entities.Bill;
import com.example.billservice.repository.BillRepository;
import com.example.billservice.repository.ProductItemsRepository;
import com.example.billservice.service.CustomerRestClient;
import com.example.billservice.service.ProductRestClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {
    private final BillRepository billRepository;
    private final ProductItemsRepository productItemsRepository;
    private final CustomerRestClient customerRestClient;
    private final ProductRestClient productRestClient;

    public BillController(BillRepository billRepository, ProductItemsRepository productItemsRepository, CustomerRestClient customerRestClient, ProductRestClient productRestClient) {
        this.billRepository = billRepository;
        this.productItemsRepository = productItemsRepository;
        this.customerRestClient = customerRestClient;
        this.productRestClient = productRestClient;
    }

    @GetMapping(path = "/fullBill/{id}")
    public Bill getBill(@PathVariable long id){
        Bill bill=billRepository.findById(id).orElse(null);
        bill.setCustomer(customerRestClient.findCustomerById(bill.getCustomerId()));
        bill.getProductItems().forEach(p ->{
            p.setProduct(productRestClient.findProductById(p.getProductId()));
        });
        return bill;
    }
}
