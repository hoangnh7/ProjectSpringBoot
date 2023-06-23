package com.example.demo.model.mapper;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.model.dto.OrderInfoDto;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderMapper {
    @Autowired
    private ProductRepository productRepository;

    public static OrderInfoDto toOrderInfoDto(Order order){

        OrderInfoDto tmp= new OrderInfoDto();
        tmp.setSizeVn(order.getSize());
        tmp.setTotalPrice(order.getTotalPrice());
//        tmp.setProductImg(order.getProduct().getOnfeetImages().get(0));
        tmp.setId(order.getId());
//        tmp.setProductName(order.getProduct().getName());
        return tmp;
    }
}
