package com.example.demo.controller.admin;

import com.example.demo.entity.Order;
import com.example.demo.model.request.UpdateOrderReq;
import com.example.demo.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class ManageOrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/admin/orders")
    public String getOrder(Model model){
        List<Order> orders = orderService.adminGetListOrder();
        model.addAttribute("orders",orders);
        System.out.println(orders.get(0).getProduct().getName());
        return "admin/order/list";
    }
    @GetMapping("/admin/orders/{id}")
    public String getOrderById(Model model, @PathVariable long id){
        Order order = orderService.getOrderById(id);
        model.addAttribute("order",order);
        return "admin/order/detail";
    }
    @PutMapping("/api/admin/orders/{id}/update-status")
    public ResponseEntity<?>updateOrder(@RequestBody  UpdateOrderReq req, @PathVariable long id){
        Order order = orderService.updateOrder( req, id);
        return ResponseEntity.ok(order);
    }
    @GetMapping("/admin/orders/create")
    public String getCreateOrder(){
        return "admin/order/create";
    }

}
