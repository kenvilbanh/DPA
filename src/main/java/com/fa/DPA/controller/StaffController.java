package com.fa.DPA.controller;

import com.fa.DPA.constant.Constant;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.StaffAccountRepository;
import com.fa.DPA.service.StaffAccountService;
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
@RequestMapping("/staff")
public class StaffController {
    private StaffAccountService staffService;

    @Autowired
    public StaffController(StaffAccountService staffService) {
        this.staffService = staffService;
    }


    /**
     *
     * @param page
     * @return
     */
    @GetMapping
    public ResponseEntity<Map<String, Object> > getAccountPaging(@RequestParam(defaultValue = Constant.DEFAULT_NUM_PAGE) int page){
        Map<String, Object> response = new HashMap<>();
        List<StaffAccount> staffAccountList;
        Pageable pageable = PageRequest.of(page, Constant.DEFAULT_PAGE_SIZE);
        try{
            Page<StaffAccount> staffAccountPage = staffService.getAllStaffPaging(pageable);
            staffAccountList = staffAccountPage.getContent();
            response.put("contactList", staffAccountList);
            response.put("currentPage", staffAccountPage.getNumber());
            response.put("totalPage", staffAccountPage.getTotalPages());
            response.put("totalItem", staffAccountPage.getTotalElements());
        }catch (Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    HttpHeaders httpHeaders = new HttpHeaders();

    /**
     *
     * @param staffAccount
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<StaffAccount> createAccount(@RequestBody StaffAccount staffAccount) {
        StaffAccount staffAccountReturn = staffService.createAccount(staffAccount);
        if(staffAccountReturn == null){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            httpHeaders.setLocation(new URI("/staff"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    /**
     *
     * @param staffAccount
     * @return
     */
    @PutMapping("/edit")
    public ResponseEntity<StaffAccount> changeRole(@RequestBody StaffAccount staffAccount){
        Long id = staffAccount.getId();
        Long idRole = staffAccount.getRole().getId();
        try{
            staffService.modifyRoleOrStatus(id, idRole);
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            httpHeaders.setLocation(new URI("/staff"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }


    /**
     *
     * @param staffAccount
     * @return
     */

    @DeleteMapping("/delete")
    public ResponseEntity<StaffAccount> deactiveAccount(@RequestBody StaffAccount staffAccount){
        Long id = staffAccount.getId();
        try{
            staffService.modifyRoleOrStatus(id, null);
        }catch(Exception ex){
            System.out.println(ex);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        try {
            httpHeaders.setLocation(new URI("/staff"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }



}

