package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product_size")
@Table(name = "product_size")
public class ProductSize {
    @Id
    @Column(name="product_id")
    private String productId;
    @Column(name = "size")
    private int size;
    @Column(name = "quantity")
    private int quantity;
}
