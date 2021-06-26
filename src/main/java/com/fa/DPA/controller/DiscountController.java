package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.Discount;
import com.fa.DPA.repos.BaseRepository;
import com.fa.DPA.repos.DiscountRepository;
import com.fa.DPA.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class DiscountController {
    private DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    HttpHeaders httpHeaders = new HttpHeaders();

    @PostMapping(value = "/discount")
    public ResponseEntity<Discount> createDiscount(@RequestBody Discount discount){
        System.out.println(discount);
        Discount discountReturn = discountService.saveDiscount(discount);
        System.out.println(discountReturn);
        if(discountReturn == null){
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

    @PutMapping(value = "/discount/{id}")
    public ResponseEntity<Discount> update(@RequestBody Discount discount, @PathVariable("id") long id){
        discount.setId(id);
        discount = discountService.saveDiscount(discount);
        if(discount == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        try{
            httpHeaders.setLocation(new URI("/discount"));

        }  catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


    @GetMapping(value = "/discount")
    public ResponseEntity<Map<String, Object>> getPage(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) int page){
        Map<String, Object> responese = new HashMap<>();
        List<Discount> discountsList ;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try{
            Page<Discount> discountPaging = discountService.getAllDiscount(pageable);
            discountsList = discountPaging.getContent();
            responese.put("discountList", discountsList);
            responese.put("currentPage", discountPaging.getNumber());
            responese.put("totalPage", discountPaging.getTotalPages());
            responese.put("totalItem", discountPaging.getTotalElements());

        } catch (Exception ex){
            System.out.println(ex);
            return  new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responese, HttpStatus.OK);
    }




    }




