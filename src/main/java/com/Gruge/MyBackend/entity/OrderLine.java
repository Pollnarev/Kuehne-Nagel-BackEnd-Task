package com.Gruge.MyBackend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_lines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @OneToOne
    @JoinColumn(name = "product", nullable = false, unique = true)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;
}
