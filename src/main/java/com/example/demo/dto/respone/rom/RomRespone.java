package com.example.demo.dto.respone.rom;

import com.example.demo.dto.respone.product.ProductDetailStatusRespone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RomRespone {
    private String id;
    private String name;
    private List<ProductDetailStatusRespone> productDetailStatusRespones;
}
