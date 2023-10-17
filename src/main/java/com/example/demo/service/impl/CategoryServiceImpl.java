package com.example.demo.service.impl;


import com.example.demo.entity.CategoryEntity;
import com.example.demo.repo.CategoryRepo;
import com.example.demo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public List<CategoryEntity> findByCategoryAndDeleteFlagIsFalse() {
        return categoryRepo.findByDeleteFlagIsFalse();
    }
}
