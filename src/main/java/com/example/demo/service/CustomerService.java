package com.example.demo.service;


import com.example.demo.dto.request.customer.CustomerRequest;
import com.example.demo.entity.CustomerEntity;

import java.text.ParseException;

public interface CustomerService {

    CustomerEntity findByEmail(String email);

    String addCustomer(CustomerRequest request) throws ParseException;

    CustomerEntity findByPhone(String phone);
}
