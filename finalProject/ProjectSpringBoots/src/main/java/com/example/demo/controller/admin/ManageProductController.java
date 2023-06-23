package com.example.demo.controller.admin;

import com.example.demo.entity.Brand;
import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.model.dto.CategoryInfo;
import com.example.demo.service.BrandService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ManageProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private BrandService brandService;
    @GetMapping("/admin/products")
    public String getProductManagePage(Model model ){
    List<Product> products = productService.adminGetListProduct();
    model.addAttribute("products",products);
//        System.out.println(products.get(0).getP);
    List<CategoryInfo>categories = categoryService.getListCategory();
    model.addAttribute("categories",categories);
    List<Brand> brands=brandService.getListBrand();
    model.addAttribute("brands",brands);

    return "admin/product/list.html";
    }

}
