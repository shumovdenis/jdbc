package ru.netology.jdbc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;



@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private Date date;
    @Column(name = "customer_id", insertable = false, updatable = false)
    private int customer_id;
    private String productName;
    private int amount;

    @ManyToOne
    private Customer customer;


}
