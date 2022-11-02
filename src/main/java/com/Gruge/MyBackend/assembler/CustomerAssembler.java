package com.Gruge.MyBackend.assembler;

import com.Gruge.MyBackend.controller.CustomerController;
import com.Gruge.MyBackend.entity.Customer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CustomerAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {
    @Override
    public EntityModel<Customer> toModel(Customer customer) {
        return EntityModel.of(customer,
                linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel(),
                linkTo(methodOn(CustomerController.class).all()).withRel("customers"));
    }
}
