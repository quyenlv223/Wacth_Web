package com.example.demo.service;

import com.example.demo.dto.respone.product.NewProductRespone;
import com.example.demo.dto.respone.product.ProductRespone;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface ProductService {

    List<NewProductRespone> findAll(Pageable pageable);

    ProductRespone findById(Long id);

    List<NewProductRespone> findByCateId(Long id);

    List<NewProductRespone> findByAppleWatch();

    List<NewProductRespone> findBySamSung();

    List<NewProductRespone> findByXiaomi();

    List<NewProductRespone> findByHuawei();

    List<NewProductRespone> findByGarmin();

    List<NewProductRespone> findbyTop10();

    List<NewProductRespone> findByName(String name);

    List<NewProductRespone> findbyRandom();
}
