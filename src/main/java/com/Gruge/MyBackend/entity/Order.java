package com.Gruge.MyBackend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "orderr", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @Column(name = "order_lines", nullable = false)
    private List<OrderLine> orderLines;

    @OneToOne
    @JoinColumn(name = "customer", nullable = false)
    private Customer customer;

    @Column(name = "submission_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date submissionDate;
}
