package com.example.demo.service;




import com.example.demo.dto.respone.product.ProductDetailStatusRespone;

import java.util.List;

public interface ProductDetailStatusService {

    List<ProductDetailStatusRespone> findByRomId(Long romId);
}
