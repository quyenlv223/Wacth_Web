package com.example.demo.dto.respone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VPResponDto {
    private String code;
    private String message;
    private String data;
    
}
