package com.example.demo.service.impl;


import com.example.demo.dto.respone.rom.RomRespone;
import com.example.demo.entity.RomEntity;
import com.example.demo.repo.RomRepo;
import com.example.demo.service.ProductDetailStatusService;
import com.example.demo.service.RomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RomServiceImpl implements RomService {
    private final RomRepo repo;

    private final ProductDetailStatusService productDetailStatusService;

    @Override
    public List<RomRespone> findByProductId(Long id) {
        List<RomRespone> list = new ArrayList<>();
        List<RomEntity> romEntities = repo.findByProductId(id);
        romEntities.forEach(o -> {
            if (productDetailStatusService.findByRomId(o.getId()) != null && productDetailStatusService.findByRomId(o.getId()).size() > 0){
                list.add(RomRespone.builder()
                        .name(o.getName())
                        .id(String.valueOf(o.getId()))
                        .productDetailStatusRespones(productDetailStatusService.findByRomId(o.getId()))
                        .build());
            }

        });

        return list;
    }

    private RomRespone mapToEntity(RomEntity entity){
        RomRespone respone = new RomRespone();
        respone.setId(String.valueOf(entity.getId()));
        respone.setName(entity.getName());
        return respone;
    }
}
