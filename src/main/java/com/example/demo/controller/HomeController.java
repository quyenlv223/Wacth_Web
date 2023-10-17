package com.example.demo.controller;

import com.example.demo.dto.respone.product.NewProductRespone;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;


    @GetMapping("all")
    public String trangChu1(Model model , @RequestParam("page") String page) {
        //PageRequest pageRequest = PageRequest.of(Integer.parseInt(page) - 1, 16);
        List<NewProductRespone> list = productService.findAll(PageRequest.of(Integer.valueOf(page) - 1, 8));
        model.addAttribute("list" , list);
        return "views/home/shop";
    }



    @GetMapping("")
    public String trangChu(Model model) {
        List<NewProductRespone> listAppleWatch = productService.findByAppleWatch();
        List<NewProductRespone> listSS = productService.findBySamSung();
        List<NewProductRespone> listXiaomi = productService.findByXiaomi();
        List<NewProductRespone> listHuawei = productService.findByHuawei();
        List<NewProductRespone> listTop10 = productService.findbyTop10();
        List<NewProductRespone> listGarmin = productService.findByGarmin();
        List<NewProductRespone> listRandom = productService.findbyRandom();
        List<NewProductRespone> list = new ArrayList<>();
        if (listRandom.size() > 2){
            for(int i = listRandom.size() - 1; i > listRandom.size() -3; i--){
                list.add(listRandom.get(i));
            }
        }

        model.addAttribute("list", list);
        model.addAttribute("listAppleWatch", listAppleWatch);
        model.addAttribute("listSS", listSS);
        model.addAttribute("listXiaomi", listXiaomi);
        model.addAttribute("listHuawei", listHuawei);
        model.addAttribute("listTop10", listTop10);
        model.addAttribute("listGarmin", listGarmin);
        model.addAttribute("listRandom", listRandom);
        return "views/home/trangchu";
    }
    @GetMapping("{id}")
    public String getProductByCategori(Model model, @PathVariable("id") String id) {
        List<NewProductRespone> listIps = productService.findByCateId(Long.parseLong(id));
        model.addAttribute("list", listIps);
        return "views/home/shop";
    }
}
