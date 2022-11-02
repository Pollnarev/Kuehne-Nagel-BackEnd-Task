package com.Gruge.MyBackend.service;

import com.Gruge.MyBackend.assembler.OrderAssembler;
import com.Gruge.MyBackend.controller.OrderController;
import com.Gruge.MyBackend.dao.OrderRepository;
import com.Gruge.MyBackend.entity.Order;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class OrderService implements BasicService<Order> {
    private OrderRepository orderRepository;

    private OrderAssembler orderAssembler;

    @Autowired
    private void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    private void setOrderAssembler(OrderAssembler orderAssembler) {
        this.orderAssembler = orderAssembler;
    }

    @Override
    public CollectionModel<EntityModel<Order>> getAll() {
        List<EntityModel<Order>> orders = orderRepository.findAll().stream()
                .map(orderAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all()).withSelfRel());
    }

    @SneakyThrows
    public CollectionModel<EntityModel<Order>> getAllByDate(String isoDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = simpleDateFormat.parse(isoDate);

        List<EntityModel<Order>> orders = orderRepository.findAllBySubmissionDate(date).stream()
                .map(orderAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(orders, linkTo(methodOn(OrderController.class).all(isoDate)).withSelfRel());
    }

    @Override
    public EntityModel<Order> add(Order order) {
        return orderAssembler.toModel(orderRepository.save(order));
    }

    @Override
    public EntityModel<Order> getOne(int id) {
        if (orderRepository.findById(id).isEmpty())
            return null;

        return orderAssembler.toModel(
                orderRepository.findById(id).get()
        );
    }
}
