package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.model.dto.OrderInfoDto;
import com.example.demo.model.request.CreateOrderReq;
import com.example.demo.model.request.UpdateOrderReq;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    public Order CreateOrder(CreateOrderReq req, User user);
    public List<OrderInfoDto>getListOrderById(long id);
    public List<Order>adminGetListOrder();
    public Order getOrderById(long id);
    public Order adminCreateOrder(CreateOrderReq req);
    public Order updateOrder(UpdateOrderReq req,long id);
}
