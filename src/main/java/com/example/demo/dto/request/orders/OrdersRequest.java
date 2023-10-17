package com.example.demo.dto.request.orders;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersRequest {
    private Long id;
    private String address;
    private Long totalMoney;
    private String voucherCode;
    private String status;
    private String note;
    private String statusBy;
}
