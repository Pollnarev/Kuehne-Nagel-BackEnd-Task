package com.Gruge.MyBackend.controller;

import com.Gruge.MyBackend.entity.Order;
import com.Gruge.MyBackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private OrderService orderService;

    @Autowired
    private void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping(params = {})
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(orderService.getAll(), HttpStatus.OK);
    }

    @GetMapping(params = {"isoDate"})
    public ResponseEntity<?> all(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String isoDate) {
        return new ResponseEntity<>(orderService.getAllByDate(isoDate), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable("id") int id) {
        return new ResponseEntity<>(orderService.getOne(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody Order order) {
        EntityModel<Order> orderEntityModel = orderService.add(order);

        return ResponseEntity
                .created(orderEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(orderEntityModel);
    }
}
