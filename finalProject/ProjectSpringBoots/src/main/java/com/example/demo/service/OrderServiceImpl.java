package com.example.demo.service;

import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.entity.ProductSize;
import com.example.demo.entity.User;
import com.example.demo.exception.NotFoundException;
import com.example.demo.model.dto.OrderInfoDto;
import com.example.demo.model.mapper.OrderMapper;
import com.example.demo.model.request.CreateOrderReq;
import com.example.demo.model.request.UpdateOrderReq;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ProductSizeRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductSizeRepository productSizeRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public Order CreateOrder(CreateOrderReq req, User user) {
        Order order = new Order();
        order.setReceiverPhone(req.getReceiverPhone());
        order.setTotalPrice(req.getTotalPrice());
        order.setReceiverName(req.getReceiverName());
        order.setReceiverAddress(req.getReceiverAddress());
        order.setSize(req.getSize());
        order.setCreatedBy(user);
        order.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        order.setNote(null);
        order.setBuyer(user);
        order.setStatus(1   );
        order.setModifiedBy(user);
        Product product = productRepository.findById(req.getProductId());

        order.setProduct(product);

        order.setNote(req.getNote());
        order.setProductPrice(req.getProductPrice());
        order.setTotalPrice(req.getTotalPrice());
        order.setStatus(5);
        orderRepository.save(order);

        return order;
    }

    @Override
    public List<OrderInfoDto> getListOrderById(long id) {
        List<Order> orders = orderRepository.findByBuyer(id);
        List<OrderInfoDto>orderInfoDtos = new ArrayList<OrderInfoDto>();
        for (Order order: orders){
            orderInfoDtos.add(OrderMapper.toOrderInfoDto(order));
//            Product product= productRepository.findById(order.getProduct().getId());

        }

        return orderInfoDtos;

    }

    @Override
    public List<Order> adminGetListOrder() {
        List<Order>orders= orderRepository.findAll();

        return orders;
    }

    @Override
    public Order getOrderById(long id) {
        Order order = orderRepository.getById(id);
        return order;
    }

    @Override
    public Order adminCreateOrder(CreateOrderReq req) {


        return null;
    }

    @Override
    public Order updateOrder(UpdateOrderReq req,long id) {
        Optional<Order> order= orderRepository.findById(id);
//        order.setSize(req.getSize());
        Order order1= order.get();
        order1.setStatus(req.getStatus());
        orderRepository.save(order1);
        return order1;
    }
}
