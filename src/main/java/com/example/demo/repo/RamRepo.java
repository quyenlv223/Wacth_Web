package com.example.demo.repo;

import com.example.demo.entity.RamEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface RamRepo extends JpaRepository<RamEntity, Long> {

    List<RamEntity> findByDeleteFlagIsFalse();
}
