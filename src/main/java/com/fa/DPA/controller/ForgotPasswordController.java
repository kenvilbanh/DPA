package com.fa.DPA.controller;

import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forgot-password")
public class ForgotPasswordController {
    private CustomerAccountService customerAccountService;

    private JavaMailSender mailSender;

    @Autowired
    public ForgotPasswordController(CustomerAccountService customerAccountService, JavaMailSender mailSender) {
        this.customerAccountService = customerAccountService;
        this.mailSender = mailSender;
    }


    @PostMapping("/validate-email")
    public ResponseEntity<CustomerAccount> checkEmailExist(@RequestBody String email){
        CustomerAccount customerAccount = customerAccountService.findByEmail(email);
        if(customerAccount == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(customerAccount, HttpStatus.OK);
    }
}
