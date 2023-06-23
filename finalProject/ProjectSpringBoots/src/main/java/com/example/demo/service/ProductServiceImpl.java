package com.example.demo.service;

import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSize;
import com.example.demo.entity.Promotion;
import com.example.demo.model.dto.ProductInfo;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductSizeRepository;
import com.example.demo.repository.PromotionRepository;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PromotionRepository promotionRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private EntityManager entityManager;
    @Override
    public List<Product> getListNewProduct() {
        List<Product> products = productRepository.findAllByOrderByCreatedAtDesc();
        List<Product> products1 = new ArrayList<Product>();
        for (int i = 0; i <5; i++){
            products1.add(products.get(i));
        }
        return products1;
    }

    @Override
    public List<Product> adminGetListProduct() {
        List<Product> products = productRepository.findAllByOrderByCreatedAtDesc();
        return products;
    }

    @Override
    public List<Product> getBestSellerProduct() {
        List<Product> products = productRepository.findAllByOrderByTotalSoldDesc();
        List<Product> products1 = new ArrayList<Product>();
        for (int i=0; i<5 ;i++){
            products1.add(products.get(i));
        }
        return products1;
    }

    @Override
    public Product getProductById(String   id) {
        Product product = productRepository.findById(id);
        return product;
    }

    @Override
    public Long calPromotionPrice(Product product) {
        Long price = product.getPrice();
        List<Promotion> promotions = promotionRepository.findAllByIsActiveAndIsPublic(true,true);

        return null;
    }

    @Override
    public List<Integer> getListProductSize(String id) {
        List<Integer> productSizes = productSizeRepository.findProductSize(id);
        return productSizes;
    }

    @Override
    public Page<Product> getListProduct(int page) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page, 8, Sort.by("name").descending()));
        return products;

    }

    @Override
    public List<Product> getListProductByName(String keyword) throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();

        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder()
                .forEntity(Product.class)
                .get();
        org.apache.lucene.search.Query query= queryBuilder
                .keyword()
                .wildcard()
                .onField("name")
                .matching("*"+keyword)
                .createQuery();
        org.hibernate.search.jpa.FullTextQuery jpaQuery =fullTextEntityManager.createFullTextQuery(query,Product.class);
        List<Product> products =jpaQuery.getResultList();
        return products;


    }
}
