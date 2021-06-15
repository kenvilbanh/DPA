package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.CustomerContact;
import com.fa.DPA.service.ContactService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contact")
public class ContactController {
    private ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     *
     * @param page
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object>> getPage(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) int page){
        Map<String, Object> response = new HashMap<>();
        List<CustomerContact> contactList ;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try{
            Page<CustomerContact> contactPaging = contactService.getAllContactPaging(pageable);
            contactList = contactPaging.getContent();
            response.put("contactList", contactList);
            response.put("currentPage", contactPaging.getNumber());
            response.put("totalPage", contactPaging.getTotalPages());
            response.put("totalItem", contactPaging.getTotalElements());
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    /**
     *
     * @param id
     * @return
     * @throws URISyntaxException
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteContact(@PathVariable("id") long id) throws URISyntaxException {
        try{
            contactService.softDelete(id);
        }catch (Exception ex){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(new URI("/contact"));
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


}
