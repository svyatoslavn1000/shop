package com.geekbrains.market.services;

import com.geekbrains.market.entities.Product;
import com.geekbrains.market.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> findAll(Specification<Product> spec, Pageable pageable) {
        return productRepository.findAll(spec, pageable);
    }

//    public List<Product> findAll() {
//        return productRepository.findAll();
//    }

    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }


    public Product save(Product Product) {
        return productRepository.save(Product);
    }
}
