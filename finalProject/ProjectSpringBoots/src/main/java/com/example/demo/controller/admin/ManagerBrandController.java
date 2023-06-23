package com.example.demo.controller.admin;

import com.example.demo.entity.Brand;
import com.example.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ManagerBrandController {
    @Autowired
    private BrandService brandService;
    @GetMapping("/admin/brands")
    public String getBrand(Model model){
        List<Brand> brands = brandService.getListBrand();
        model.addAttribute("brands",brands);
        System.out.println(brands.size());
        return "admin/brand/list.html";
    }

}
