package com.example.demo.controller.anonymous;

import com.example.demo.entity.*;
import com.example.demo.model.dto.CategoryInfo;
import com.example.demo.model.request.CreateOrderReq;
import com.example.demo.model.request.UpdateUserReq;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.BrandService;
import com.example.demo.service.CategoryService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ShopController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/")
    public String getIndex(Model model) {
        List<Product> products = productService.getListNewProduct();
        List<Product> bestSellerProducts = productService.getBestSellerProduct();
        model.addAttribute("newProducts",products);
        model.addAttribute("bestSellerProducts",bestSellerProducts);

        return "shop/index";
    }
    @GetMapping("/san-pham/{slug}/{id}")
    public String getDetailShop(Model model, @PathVariable String id) {
        Product product = productService.getProductById(id);
        Boolean canBuy;
        if (product.getIsAvailable()){
            canBuy= true;
        }else {
            canBuy=false;
        }
        model.addAttribute("product",product);
        model.addAttribute("canBuy",canBuy);
        return "shop/detail";
    }
    @GetMapping("/dat-hang/{slug}/{id}")
    public String getPayment(Model model, @PathVariable String id) {
        Product product = productService.getProductById(id);
        List<Integer> productSize = productService.getListProductSize(id);
        model.addAttribute("product",product);
        model.addAttribute("product_id",product.getId().toString().toUpperCase());
        model.addAttribute("availableSizes",productSize);
        for ( Integer size : productSize){
            System.out.println(size);
        }
        System.out.println(""+product.getId().toString());
        return "shop/payment";
    }
//    @GetMapping("/tin-tuc")
//    public String getNews(Model model) {
//        return "shop/news";
//    }
    @GetMapping("/san-pham")
    public String getShop(Model model , @RequestParam(required = false) Integer page) {
        if (page==null){
            page = 0;
        } else  {
            page--;
            if (page < 0) {
                page = 0;
            }
        }
        Page<Product> products = productService.getListProduct(page);
        List<Brand> brands = brandService.getListBrand();
        model.addAttribute("brands",brands);
        System.out.println(brands.get(0).getName());
        List<CategoryInfo>categories = categoryService.getListCategory();
        model.addAttribute("categories",categories);
        model.addAttribute("listProduct",products.getContent());
        model.addAttribute("totalPages", products.getTotalPages());
        model.addAttribute("currentPage", ++page);
//        model.addAttribute("listProduct", products.getItems());
        return "shop/shop";
    }
    @GetMapping("/api/tim-kiem")
    public String getSearch(Model model , @RequestParam(required = false) String keyword) throws InterruptedException {
        List<Product> result = productService.getListProductByName(keyword);
        model.addAttribute("listProduct",result);
        return "shop/search";

    }
    @PostMapping("/api/order")
    public ResponseEntity<?> postorder(@RequestBody CreateOrderReq req){
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        Order order = orderService.CreateOrder(req,user);
        return ResponseEntity.ok(order);
    }
}
