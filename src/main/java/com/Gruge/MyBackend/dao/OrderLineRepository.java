package com.Gruge.MyBackend.dao;

import com.Gruge.MyBackend.entity.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}
