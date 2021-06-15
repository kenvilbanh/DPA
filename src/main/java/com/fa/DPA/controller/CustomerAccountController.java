package com.fa.DPA.controller;

import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.service.CustomerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/customer")
public class CustomerAccountController {
    private CustomerAccountService customerAccountService;

    @Autowired
    public CustomerAccountController(CustomerAccountService customerAccountService) {
        this.customerAccountService = customerAccountService;
    }

    HttpHeaders httpHeaders = new HttpHeaders();

    /**
     *
     * @param customerAccount
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<CustomerAccount> register(CustomerAccount customerAccount){
        CustomerAccount customerAccountReturn = customerAccountService.saveAccount(customerAccount);
        if(customerAccountReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            httpHeaders.setLocation(new URI("/home"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/view/id")
    public ResponseEntity<CustomerAccount> viewInfo(@PathVariable("id") long id){
        CustomerAccount customerAccountReturn = customerAccountService.findById(id);
        if(customerAccountReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(customerAccountReturn, HttpStatus.OK);
    }

//    @PostMapping("edit/id")
//    public ResponseEntity<CustomerAccount> editInfo(@PathVariable("id") long id){
//        CustomerAccount customerAccountReturn = customerAccountService.findById(id);
//        if(customerAccountReturn == null){
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//        return new ResponseEntity<>(customerAccountReturn, HttpStatus.OK);
//    }
}
