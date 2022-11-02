package com.Gruge.MyBackend.service;

import com.Gruge.MyBackend.assembler.CustomerAssembler;
import com.Gruge.MyBackend.controller.CustomerController;
import com.Gruge.MyBackend.dao.CustomerRepository;
import com.Gruge.MyBackend.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class CustomerService implements BasicService<Customer> {
    private CustomerRepository customerRepository;

    private CustomerAssembler customerAssembler;

    @Autowired
    private void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    private void setCustomerAssembler(CustomerAssembler customerAssembler) {
        this.customerAssembler = customerAssembler;
    }

    @Override
    public CollectionModel<EntityModel<Customer>> getAll() {
        List<EntityModel<Customer>> customers = customerRepository.findAll().stream()
                .map(customerAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
    }

    @Override
    public EntityModel<Customer> add(Customer customer) {
        return customerAssembler.toModel(customerRepository.save(customer));
    }

    @Override
    public EntityModel<Customer> getOne(int id) {
        if (customerRepository.findById(id).isEmpty())
            return null;

        return customerAssembler.toModel(
                customerRepository.findById(id).get()
        );
    }
}
