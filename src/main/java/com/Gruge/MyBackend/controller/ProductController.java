package com.Gruge.MyBackend.controller;

import com.Gruge.MyBackend.entity.Product;
import com.Gruge.MyBackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    private void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<?> all() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> one(@PathVariable("id") int id) {
        return new ResponseEntity<>(productService.getOne(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody Product product) {
        EntityModel<Product> productEntityModel = productService.add(product);

        return ResponseEntity
                .created(productEntityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(productEntityModel);
    }
}
