package com.example.demo.repo;

import com.example.demo.entity.ProductDetailStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ProductDetailStatusRepo extends JpaRepository<ProductDetailStatusEntity, Long> {

    @Query("select o from ProductDetailStatusEntity o where o.deleteFlag = false and o.status = 'ON' and o.romId = ?1")
    List<ProductDetailStatusEntity> findByRomId(Long id);

    ProductDetailStatusEntity findByDeleteFlagIsFalseAndId(Long id);
}
