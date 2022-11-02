package com.Gruge.MyBackend.service;

import com.Gruge.MyBackend.assembler.ProductAssembler;
import com.Gruge.MyBackend.controller.ProductController;
import com.Gruge.MyBackend.dao.ProductRepository;
import com.Gruge.MyBackend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProductService implements BasicService<Product> {
    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductAssembler productAssembler;

    @Autowired
    private void setProductAssembler(ProductAssembler productAssembler) {
        this.productAssembler = productAssembler;
    }

    @Override
    public CollectionModel<EntityModel<Product>> getAll() {
        List<EntityModel<Product>> products = productRepository.findAll().stream()
                .map(productAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    @Override
    public EntityModel<Product> add(Product product) {
        return productAssembler.toModel(productRepository.save(product));
    }

    @Override
    public EntityModel<Product> getOne(int id) {
        if (productRepository.findById(id).isEmpty())
            return null;

        return productAssembler.toModel(
                productRepository.findById(id).get()
        );
    }
}
