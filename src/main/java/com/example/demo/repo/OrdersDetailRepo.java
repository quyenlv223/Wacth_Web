package com.example.demo.repo;

import com.example.demo.entity.OrdersDetailEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


@Repository
public interface OrdersDetailRepo extends JpaRepository<OrdersDetailEntity, Long> {
    @Query("select o from OrdersDetailEntity o where o.deleteFlag = false and o.ordersEntity.id = ?1")
    List<OrdersDetailEntity> findByDeleteFlagIsFalseAndOrdersEntity(Long id);
}
