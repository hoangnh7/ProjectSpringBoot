package com.example.demo.service;

import com.example.demo.entity.Post;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSize;
import com.example.demo.model.dto.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    public List<Product> getListNewProduct();
    public List<Product> adminGetListProduct();
    public List<Product> getBestSellerProduct();
    public Product getProductById(String id);
    public Long calPromotionPrice(Product product);
    public List<Integer>getListProductSize(String id);
    public Page<Product>getListProduct(int page);
    public List<Product> getListProductByName(String keyword) throws InterruptedException;
}
