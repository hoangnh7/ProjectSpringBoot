package com.example.demo.controller.anonymous;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShopController {
    @GetMapping("/")
    public String getIndex(Model model) {
        return "shop/index";
    }
//    @GetMapping("/index.html")
//    public String getIndexPage(Model model) {
//        return "shop/index";
//    }
////    @GetMapping("/tin-tuc")
////    public String getNews(Model model) {
////        return "shop/news";
////    }
    @GetMapping("/shop.html")
    public String getShop(Model model) {
        return "shop/shop";
    }
}
