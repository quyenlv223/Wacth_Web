package com.example.demo.dto.respone.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailStatusRespone {
    private String id;
    private String priceString;
    private String priceNow;
    private long price;
    private long quantity;
    private String status;
    private String color;
    private String pricePromotionString;
    private long pricePromotion;
    private long idPromotion;
}
