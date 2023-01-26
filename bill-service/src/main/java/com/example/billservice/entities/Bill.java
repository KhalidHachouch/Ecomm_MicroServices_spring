package com.example.billservice.entities;

import com.example.billservice.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@Entity
@Data
@NoArgsConstructor@AllArgsConstructor@Builder
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date billDate;
    private long customerId;
    @OneToMany(mappedBy = "bill",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<ProductItems>productItems;
    @Transient
    private Customer customer;

}
