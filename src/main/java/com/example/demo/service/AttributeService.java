package com.example.demo.service;


import com.example.demo.dto.respone.attribute.AttributeRespone;

public interface AttributeService {
    AttributeRespone findByProduct(Long id);
}
