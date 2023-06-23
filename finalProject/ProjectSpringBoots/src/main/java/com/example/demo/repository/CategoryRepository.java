package com.example.demo.repository;

import com.example.demo.entity.Category;
import com.example.demo.model.dto.CategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Integer> {
    @Query(nativeQuery = true, name = "getListCategoryAndProductCount")
    public List<CategoryInfo> getListCategoryAndProductCount();
}
