package com.example.demo.repo;

import com.example.demo.entity.CartEntity;
import com.example.demo.entity.ProductDetailStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<CartEntity, Long> {

    List<CartEntity> findByDeleteFlagIsFalseAndIdCustomer(Long id);

    CartEntity findByDeleteFlagIsFalseAndIdCustomerAndIdProduct(Long id, ProductDetailStatusEntity propertyEntity);

    CartEntity findByDeleteFlagIsFalseAndId(Long id);
}
