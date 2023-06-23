package com.example.demo.controller.admin;

import com.example.demo.entity.Category;
import com.example.demo.model.dto.CategoryInfo;
import com.example.demo.model.request.CreateCategoryReq;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ManagerCategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/admin/categories")
    public String getCategoryManagePage(Model model) {
        List<CategoryInfo> categories = categoryService.getListCategory();
        model.addAttribute("categories", categories);

        return "admin/category/list";
    }
    @PostMapping("api/admin/categories")
    public ResponseEntity<?>createCategory(@Valid @RequestBody CreateCategoryReq req){
        Category categoryInfo = categoryService.createCategory(req);
        return ResponseEntity.ok(categoryInfo);
    }
    @PutMapping("/api/admin/categories/{id}")
    public ResponseEntity<?>updateCategory(@PathVariable int id,@RequestBody CreateCategoryReq req){
        Category category=categoryService.updateCategory(id,req);
        return ResponseEntity.ok(category);
    }
    @DeleteMapping("/api/admin/categories/{id}")
    public ResponseEntity<?> deleteCategory( @PathVariable int id) {
        categoryService.deleteCategory(id);

        return ResponseEntity.ok("Xóa thành công");
    }
}
