package com.example.demo.repo;
import com.example.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepo extends JpaRepository<CustomerEntity, Long> {

    CustomerEntity findByEmail(String email);
    CustomerEntity findByPhoneNumber(String phone);
}
