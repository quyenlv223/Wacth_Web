package com.example.demo.service.impl;


import com.example.demo.entity.ColorEntity;
import com.example.demo.repo.ColorRepo;
import com.example.demo.service.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {
    private final ColorRepo repo;

    @Override
    public String findById(Long id) {
        ColorEntity entity = repo.findByDeleteFlagIsFalseAndId(id);
        if(entity != null){
            return entity.getValueColor();
        }
        return null;
    }
}
