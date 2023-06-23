package com.example.demo.service;

import com.example.demo.entity.Brand;
import com.example.demo.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandServiceImpl implements BrandService {
    @Autowired
    BrandRepository brandRepository;
    @Override
    public List<Brand> getListBrand() {
        List<Brand> brands= brandRepository.findAll();
        return brands;
    }
}
