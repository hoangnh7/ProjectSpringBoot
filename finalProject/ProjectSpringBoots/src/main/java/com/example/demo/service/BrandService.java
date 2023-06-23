package com.example.demo.service;

import com.example.demo.entity.Brand;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BrandService {
    List<Brand> getListBrand();

}
