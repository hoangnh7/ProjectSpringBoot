package com.example.demo.controller.admin;

import com.example.demo.entity.Promotion;
import com.example.demo.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ManagerPromotionController {

    @Autowired
    public PromotionService promotionService;

    @GetMapping("/admin/promotions")
    public String getCategoryManagePage(Model model) {
        List<Promotion> promotions = promotionService.getListPromotion();
        model.addAttribute("promotions",promotions);
//        System.out.println(promotions.get(0).getCreatedAt());
        return "/admin/promotion/list";
    }
    @GetMapping("/admin/promotions/{id}")
    public String getDetailPromotion(Model model, @PathVariable long id){
        Promotion promotion = promotionService.getPromotionById(id);
        model.addAttribute("promotion",promotion);
        return "/admin/promotion/detail";
    }
    @DeleteMapping("/api/admin/promotions/{id}")
    public ResponseEntity<?>deletePromotion(@PathVariable long id){
        promotionService.deletePromotion(id);
        return ResponseEntity.ok(123);
    }

}