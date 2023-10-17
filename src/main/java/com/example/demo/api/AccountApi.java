package com.example.demo.api;

import com.example.demo.dto.request.customer.CustomerRequest;
import com.example.demo.entity.CustomerEntity;
import com.example.demo.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.text.ParseException;


@RestController
@RequestMapping("/api/login")
@RequiredArgsConstructor
@Slf4j
public class AccountApi {
    private final CustomerService staffService;
    @GetMapping("/count_login_false")
    public ResponseEntity<String> getCountLoginFalse(@RequestParam("email") String email) {
        log.info("login check");
        System.out.println("hihihi");
        CustomerEntity staff = staffService.findByEmail(email);
        if (staff != null) {
            System.out.println(staff.getId());
            return ResponseEntity.status(HttpStatus.OK).body("OKE");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sai tài khoản hoặc mật khẩu");
    }

    @GetMapping("/phone")
    public ResponseEntity<String> checkPhone(@RequestParam("phone") String phone) {
        log.info("login check");
        CustomerEntity staff = staffService.findByPhone(phone);
        if (staff != null) {
            System.out.println(staff.getId());
            return ResponseEntity.status(HttpStatus.OK).body("OKE");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sai tài khoản hoặc mật khẩu");
    }


    @PostMapping("/register")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerRequest request) throws ParseException {
        log.info("add customer start");
        String staff = staffService.addCustomer(request);
        if (staff.equals("ok")) {
            return ResponseEntity.status(HttpStatus.OK).body("OKE");
        }
        return ResponseEntity.badRequest().body("false");
    }
}
