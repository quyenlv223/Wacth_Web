package com.example.demo.repo;

import com.example.demo.entity.AttributeProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AttributeProductRepo extends JpaRepository<AttributeProductEntity, Long> {
    AttributeProductEntity findByProductId(Long productId);
}
