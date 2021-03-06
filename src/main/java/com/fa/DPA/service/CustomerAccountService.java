package com.fa.DPA.service;

import com.fa.DPA.model.CustomerAccount;
import com.fa.DPA.repos.CustomerAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

@Service
public class CustomerAccountService {
    private CustomerAccountRepository customerAccountRepository;

    @Autowired
    public CustomerAccountService(CustomerAccountRepository customerAccountRepository) {
        this.customerAccountRepository = customerAccountRepository;
    }

    /**
     *
     * @param id
     * @return
     */
    public CustomerAccount findById(Long id){
        return customerAccountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @param email
     * @return
     */
    public CustomerAccount findByEmail(String email){
        return customerAccountRepository.findByEmail(email).
                orElseThrow(() -> new EntityNotFoundException("Data with this email: " + email + " is not found"));
    }


    /**
     *
     * @param token
     * @param localDateTime
     * @return
     */
    public CustomerAccount findByTokenPassword(String token, LocalDateTime localDateTime){
        return customerAccountRepository.findByResetPasswordToken(token, localDateTime)
                .orElseThrow(() -> new EntityNotFoundException("Data with this token: " + token + " is not found or unavailable"));
    }

    /**
     *
     * @param customerAccount
     * @return
     */
    public CustomerAccount saveAccount(CustomerAccount customerAccount){
        try{
            customerAccountRepository.save(customerAccount);
            return customerAccount;
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }





}
