package com.example.demo.service.impl;


import com.example.demo.dto.respone.product.ProductDetailStatusRespone;
import com.example.demo.entity.ProductDetailStatusEntity;
import com.example.demo.repo.ProductDetailStatusRepo;
import com.example.demo.service.ColorService;
import com.example.demo.service.ProductDetailStatusService;
import com.example.demo.util.ConvertUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductDetailStatusServiceImpl implements ProductDetailStatusService {

    private final ProductDetailStatusRepo repo;
    private final ConvertUtil util;

    private final ColorService colorService;

    @Override
    public List<ProductDetailStatusRespone> findByRomId(Long romId) {
        List<ProductDetailStatusEntity> entityList = repo.findByRomId(romId);
        List<ProductDetailStatusRespone> responeList = new ArrayList<>();
        entityList.forEach(o -> {
            responeList.add(ProductDetailStatusRespone.builder()
                    .price(o.getPrice())
                    .priceNow(util.moneyToStringFormat(o.getPricePromotion()*o.getPrice()/100))
                    .priceString(util.moneyToStringFormat(o.getPrice()))
                    .quantity(o.getQuantity())
                    .id(String.valueOf(o.getId()))
                    .color(colorService.findById(o.getColorId()))
                    .pricePromotion(o.getPricePromotion())
                    .pricePromotionString(util.moneyToStringFormat(o.getPricePromotion()))
                    .idPromotion(o.getPromotionId())
                    .build());
        });
        return responeList;
    }
}
