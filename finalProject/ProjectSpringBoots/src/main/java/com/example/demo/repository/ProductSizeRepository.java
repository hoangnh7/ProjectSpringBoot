package com.example.demo.repository;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductSizeRepository extends JpaRepository<ProductSize,Long> {
    @Query(value = "SELECT size FROM product_size WHERE product_id = ?1", nativeQuery = true)
     public List<Integer>findProductSize(String id);
}
