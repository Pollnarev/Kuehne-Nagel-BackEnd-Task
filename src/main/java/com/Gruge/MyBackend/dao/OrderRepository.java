package com.Gruge.MyBackend.dao;

import com.Gruge.MyBackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    public List<Order> findAllBySubmissionDate(Date submissionDate);
}
