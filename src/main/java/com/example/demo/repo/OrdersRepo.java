package com.example.demo.repo;

import com.example.demo.entity.CustomerEntity;
import com.example.demo.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity, Long> {
    @Query("select o from OrdersEntity o where o.status <> '-2' order by o.createDate desc")
    List<OrdersEntity> findByCustomerEntityOrderByCreateDateDesc(CustomerEntity entity);

    OrdersEntity findByCodeOrder(String code);
}
