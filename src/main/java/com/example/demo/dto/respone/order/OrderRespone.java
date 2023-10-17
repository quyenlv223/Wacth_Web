package com.example.demo.dto.respone.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRespone {
    private String id;
    private String codeOrder;
    private String createDate;
    private String status;
    private String totalString;

}
