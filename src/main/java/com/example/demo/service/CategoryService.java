package com.example.demo.service;

import com.example.demo.entity.CategoryEntity;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface CategoryService {
    List<CategoryEntity> findByCategoryAndDeleteFlagIsFalse();
}
