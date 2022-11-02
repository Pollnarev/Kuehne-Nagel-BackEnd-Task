package com.Gruge.MyBackend.boot;

import com.Gruge.MyBackend.dao.ProductRepository;
import com.Gruge.MyBackend.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class NestDatabase implements CommandLineRunner {
    private ProductRepository productRepository;

    @Autowired
    private void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println(">>> ");

        if (productRepository.count() != 0)
            return;

        Product product = new Product() {
            {
                setName("Example Product");
                setSkuCode("Sku Code");
                setUnitPrice(2.2F);
            }
        };

        productRepository.save(product);
    }
}
