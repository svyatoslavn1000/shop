package com.geekbrains.decembermarket.repositories;

import com.geekbrains.decembermarket.entities.Product;
import com.geekbrains.decembermarket.entities.ProductDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<ProductDto> findAllBy();
}