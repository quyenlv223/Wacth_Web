package com.example.demo.service.impl;

import com.example.demo.dto.request.orders.OrdersRequest;
import com.example.demo.dto.respone.cart.CartRespone;
import com.example.demo.dto.respone.order.OrderRespone;
import com.example.demo.entity.*;
import com.example.demo.repo.*;
import com.example.demo.service.OrderService;
import com.example.demo.util.ConvertUtil;
import com.example.demo.util.SessionUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrdersRepo ordersRepo;
    private final CartRepo cartRepo;
    private final CustomerRepo customerRepo;
    private final SessionUtil sessionUtil;
    private final VoucherRepo voucherRepo;
    private final ConvertUtil convertUtil;
    private final ColorServiceImpl colorService;
    private final ProductRepo productRepo;
    private final RomRepo romRepo;
    private final ProductDetailStatusRepo productDetailStatusRepo;
    private final OrdersDetailRepo ordersDetailRepo;



    @Override
    public String createOrder(OrdersRequest request) {
        try {
            CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
            OrdersEntity ordersEntity = new OrdersEntity();
            if (request.getVoucherCode() != null){
                long millis = System.currentTimeMillis();
                java.sql.Date date = new java.sql.Date(millis);
                VoucherEntity voucherEntity = voucherRepo.findByDeleteFlagIsFalseAndCode(date, request.getVoucherCode());
            }
            ordersEntity.setCustomerEntity(customerEntity);
            ordersEntity.setAddress(request.getAddress());
            ordersEntity.setStatusPay(0);
            ordersEntity.setNote(request.getNote());
            ordersEntity.setReceiveDate(Timestamp.valueOf(LocalDateTime.now()));
            ordersEntity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
            ordersEntity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
            ordersEntity.setStatus("0");
            ordersEntity.setTypeOrder(1);
            ordersEntity.setTotalMoney(request.getTotalMoney());
            OrdersEntity ordersEntity1 = ordersRepo.save(ordersEntity);
            ordersEntity1.setCodeOrder("HD000" + ordersEntity1.getId());
            ordersRepo.save(ordersEntity);
            List<CartEntity> cartEntities = cartRepo.findByDeleteFlagIsFalseAndIdCustomer(customerEntity.getId());
            for (CartEntity cart : cartEntities) {
                OrdersDetailEntity ordersDetailEntity = new OrdersDetailEntity();
                ordersDetailEntity.setOrdersEntity(ordersEntity1);
                ordersDetailEntity.setQuantity(cart.getQuantity());
                ordersDetailEntity.setIdPropertyProduct(cart.getIdProduct().getId());
                ordersDetailEntity.setCreateDate(Timestamp.valueOf(LocalDateTime.now()));
                ordersDetailEntity.setModifierDate(Timestamp.valueOf(LocalDateTime.now()));
                ordersDetailEntity.setPrice(cart.getIdProduct().getPricePromotion() == 0 ? cart.getIdProduct().getPrice()
                        : cart.getIdProduct().getPricePromotion());
                ordersDetailRepo.save(ordersDetailEntity);
                cartRepo.delete(cart);
            }
            return "ok";
        }catch (Exception e){
            return "false";
        }
    }

    @Override
    public String updateOrder(OrdersRequest request) {
        return null;
    }

    @Override
    public String deleteOrder(long id) {
        return null;
    }

    @Override
    public List<OrderRespone> findAllOrder() {
        CustomerEntity customerEntity = customerRepo.findByEmail(String.valueOf(sessionUtil.getObject("username")));
        List<OrdersEntity> entityList = ordersRepo.findByCustomerEntityOrderByCreateDateDesc(customerEntity);
        List<OrderRespone> list = new ArrayList<>();
        for (OrdersEntity entity: entityList
        ) {
            OrderRespone respone = new OrderRespone();
            respone.setId(String.valueOf(entity.getId()));
            respone.setCreateDate(String.valueOf(entity.getCreateDate()));
            respone.setStatus(entity.getStatus());
            respone.setCodeOrder(entity.getCodeOrder());
            respone.setTotalString(convertUtil.moneyToStringFormat(entity.getTotalMoney()));
            list.add(respone);
        }
        return list;
    }

    @Override
    public List<CartRespone> findByOrderDetail(String id) {
        OrdersEntity entity = ordersRepo.findByCodeOrder(id);
        List<OrdersDetailEntity> list = ordersDetailRepo.findByDeleteFlagIsFalseAndOrdersEntity(entity.getId());
        List<CartRespone> list1 = new ArrayList<>();
        for (OrdersDetailEntity detail: list
        ) {
            CartRespone respone = mapToRespone(detail);
            list1.add(respone);
        }
        return list1;
    }

    @Override
    public OrdersEntity findByOrder(String id) {
        OrdersEntity entity = ordersRepo.findByCodeOrder(id);
        return entity;
    }

    @Override
    public String canncelOrder(String id) {
        OrdersEntity entity = ordersRepo.findByCodeOrder(id);
        if(Integer.parseInt(entity.getStatus()) > 1){
            for (OrdersDetailEntity detail : entity.getOrdersDetailEntities()){
                ProductDetailStatusEntity propertyEntity = productDetailStatusRepo.getById(detail.getIdPropertyProduct());
                propertyEntity.setQuantity(detail.getQuantity() + propertyEntity.getQuantity());
                productDetailStatusRepo.save(propertyEntity);
            }
        }else if(Integer.parseInt(entity.getStatus()) == 1){

        }
        entity.setStatus("-1");
        ordersRepo.save(entity);
        return "ok";
    }

    @Override
    public String datLai(String id) {
        OrdersEntity entity = ordersRepo.findByCodeOrder(id);
        entity.setStatus("0");
        ordersRepo.save(entity);
        return "ok";
    }

    private CartRespone mapToRespone(OrdersDetailEntity entity){
        CartRespone respone = new CartRespone();
        ProductDetailStatusEntity propertyEntity = productDetailStatusRepo.findByDeleteFlagIsFalseAndId(entity.getIdPropertyProduct());
        respone.setId(String.valueOf(entity.getId()));
        respone.setColor(colorService.findById(propertyEntity.getColorId()));
        RomEntity romEntity = romRepo.findById(propertyEntity.getRomId()).get();
        ProductEntity productEntity = productRepo.findById(romEntity.getProductId()).get();
        respone.setImgProduct(productEntity.getImage_key());
        respone.setIdProduct(String.valueOf(productEntity.getId()));
        respone.setQuantityProduct(propertyEntity.getQuantity());
        respone.setNameProduct(productEntity.getName());
        respone.setQuantity(entity.getQuantity());
        respone.setPriceProduct(entity.getPrice());
        respone.setPriceProductString(convertUtil.moneyToStringFormat(entity.getPrice()));
        respone.setRom(romEntity.getName() + " GB");
        respone.setPriceProductPromotion(0);
        respone.setPriceProductPromotionString(convertUtil.moneyToStringFormat(0L));
        if(respone.getPriceProductPromotion() > 0){
            respone.setTotal(respone.getQuantity() * respone.getPriceProductPromotion());
            respone.setTotalString(convertUtil.moneyToStringFormat(respone.getTotal()));
        }else {
            respone.setTotal(respone.getQuantity() * respone.getPriceProduct());
            respone.setTotalString(convertUtil.moneyToStringFormat(respone.getTotal()));
        }
        return respone;
    }
}
