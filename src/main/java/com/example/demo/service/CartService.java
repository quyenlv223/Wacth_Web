package com.example.demo.service;




import com.example.demo.dto.request.cart.CartEditRequest;
import com.example.demo.dto.request.cart.CartRequest;
import com.example.demo.dto.respone.cart.CartRespone;

import java.util.List;

public interface CartService {


    List<CartRespone> findByCustomer();

    String addCart(CartRequest cartRequest);

    String deleteCart(Long id);

    String updateCart(List<CartEditRequest> list);
}
