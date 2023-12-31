package com.example.demo.repo;

import com.example.demo.entity.PinEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PinRepo extends JpaRepository<PinEntity, Long> {
    List<PinEntity> findByDeleteFlagIsFalse();
}
