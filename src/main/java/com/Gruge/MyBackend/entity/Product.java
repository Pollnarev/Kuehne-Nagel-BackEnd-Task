package com.Gruge.MyBackend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sku_code", nullable = false)
    private String skuCode;

    @Column(name = "unit_price", nullable = false)
    private float unitPrice;
}
