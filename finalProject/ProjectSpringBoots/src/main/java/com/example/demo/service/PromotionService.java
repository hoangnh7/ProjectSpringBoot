package com.example.demo.service;

import com.example.demo.entity.Promotion;
import com.example.demo.model.request.CreatePromotionReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PromotionService {
    public List<Promotion> getListPromotion();
    public Promotion getPromotionById(long id);
    public Promotion createPromotion(CreatePromotionReq req);
    public void deletePromotion(long id);
}
