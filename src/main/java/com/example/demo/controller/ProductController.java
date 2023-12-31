package com.example.demo.controller;

import com.example.demo.dto.respone.product.NewProductRespone;
import com.example.demo.dto.respone.product.ProductRespone;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @GetMapping("/{id}")
    public String getProductId(Model model, @PathVariable("id") String id){
        List<NewProductRespone> listRandom = productService.findbyRandom();

        ProductRespone respone = productService.findById(Long.valueOf(id));
        if (null == respone) {
            return "redirect:/home";
        }
        model.addAttribute("product", respone);
        model.addAttribute("listRandom", listRandom);
        return "/views/product-detail";
    }
}
