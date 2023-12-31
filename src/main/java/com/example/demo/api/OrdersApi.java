package com.example.demo.api;

import com.example.demo.dto.request.orders.OrdersRequest;
import com.example.demo.entity.OrdersEntity;
import com.example.demo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersApi {

    private final OrderService orderService;




    @GetMapping("{id}")
    public ResponseEntity<?> canncelOrder(@PathVariable String id){
        String check = orderService.canncelOrder(id);
        if(check.equals("ok")){
            return ResponseEntity.ok().body(new OrdersEntity());
        }else {
            return ResponseEntity.badRequest().body(new OrdersEntity());
        }
    }


    @GetMapping("update/{id}")
    public ResponseEntity<?> datLaiOrder(@PathVariable String id){
        String check = orderService.datLai(id);
        if(check.equals("ok")){
            return ResponseEntity.ok().body(new OrdersEntity());
        }else {
            return ResponseEntity.badRequest().body(new OrdersEntity());
        }
    }

    /*@PostMapping("/create/online")
    public ResponseEntity<?> createOrderOnline(@RequestBody OrdersRequest request , HttpServletRequest request1){
        VPResponDto check = orderService.createOrderOnline(request, request1);
        if(check != null){
            return ResponseEntity.ok().body(check);
        }else {
            return ResponseEntity.badRequest().body("false");
        }
    }*/


    @PostMapping("/create")
    public ResponseEntity<?> createOrder(@RequestBody OrdersRequest request){
        String check = orderService.createOrder(request);
        if(check.equals("ok")){
            return ResponseEntity.ok().body("ok");
        }else {
            return ResponseEntity.badRequest().body("false");
        }
    }



}
