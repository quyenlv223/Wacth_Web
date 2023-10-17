package com.example.demo.dto.request.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private String id;
    private String idProduct;
    private String quantityCart;
}
