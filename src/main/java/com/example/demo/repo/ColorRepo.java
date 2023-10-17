package com.example.demo.repo;

import com.example.demo.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ColorRepo extends JpaRepository<ColorEntity, Long> {

    ColorEntity findByDeleteFlagIsFalseAndId(Long id);
}
