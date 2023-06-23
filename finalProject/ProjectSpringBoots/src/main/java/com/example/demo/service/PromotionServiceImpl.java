package com.example.demo.service;

import com.example.demo.entity.Promotion;
import com.example.demo.exception.BadRequestException;
import com.example.demo.model.request.CreatePromotionReq;
import com.example.demo.repository.PromotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Component
public class PromotionServiceImpl implements PromotionService{
    @Autowired
    private PromotionRepository promotionRepository;
    @Override
    public List<Promotion> getListPromotion() {
        List<Promotion> promotions= promotionRepository.findAll();
        return promotions;
    }

    @Override
    public Promotion getPromotionById(long id) {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        return promotion.get();
    }

    @Override
    public Promotion createPromotion(CreatePromotionReq req) {
        Promotion promotion = new Promotion();
        promotion.setName(req.getName());
        promotion.setCouponCode(req.getCode());
        promotion.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        promotion.setExpiredAt(req.getExpiredDate());
        promotion.setDiscountType(req.getDiscountType());
        promotion.setDiscountValue(req.getDiscountValue());
        // Check has valid promotion
        return null;
    }

    @Override
    public void deletePromotion(long id) {
        Optional<Promotion> promotion = promotionRepository.findById(id);
        promotionRepository.delete(promotion.get());

    }
}
