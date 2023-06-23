package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.model.dto.CategoryInfo;
import com.example.demo.model.request.CreateCategoryReq;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    public List<CategoryInfo> getListCategory();
    public Category createCategory(CreateCategoryReq req);
    public Category updateCategory(int id,CreateCategoryReq req);
    public void deleteCategory(int id);
}
