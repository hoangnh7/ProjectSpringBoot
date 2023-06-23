package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.CategoryInfo;
import com.example.demo.model.request.CreateCategoryReq;
import com.example.demo.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CategoryInfo> getListCategory() {
        List<CategoryInfo> categories= categoryRepository.getListCategoryAndProductCount();
        return categories;
    }

    @Override
    public Category createCategory(CreateCategoryReq req) {
        Category rs = new Category();
        rs.setName(req.getName());
        categoryRepository.save(rs);
        return rs;
    }

    @Override
    public Category updateCategory(int id, CreateCategoryReq req) {
        Optional<Category> rs = categoryRepository.findById(id);
        Category category = rs.get();

        if (rs!= null){
            category.setName(req.getName());
            categoryRepository.save(category);
            return category;
        }
        else {
            throw new NotFoundException("category id not found");
        }

    }

    @Override
    public void deleteCategory(int id) {
        Optional<Category> rs = categoryRepository.findById(id);
        if (rs==null){
            throw new NotFoundException("category id not found");
        } else{
            Category category = rs.get();
            categoryRepository.delete(category);
        }
    }
}
