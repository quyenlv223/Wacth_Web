package com.example.demo.dto.respone.product;

import com.example.demo.dto.respone.attribute.AttributeRespone;
import com.example.demo.dto.respone.rom.RomRespone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRespone {
    private String id;
    private String name;
    private String imageKey;
    private String status;
    private String note;
    private List<String> srcImage;
    private List<RomRespone> romRespones;
    private AttributeRespone attributeRespone;
}
