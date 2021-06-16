package com.fa.DPA.service;

import com.fa.DPA.model.Account_Status;
import com.fa.DPA.model.Role;
import com.fa.DPA.model.StaffAccount;
import com.fa.DPA.repos.StaffAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

@Service
public class StaffAccountService {
    private StaffAccountRepository staffAccountRepository;

    /**
     * @param staffAccountRepository
     */
    @Autowired
    public StaffAccountService(StaffAccountRepository staffAccountRepository) {
        this.staffAccountRepository = staffAccountRepository;
    }

    /**
     * @param id
     * @return
     */
    public StaffAccount findById(Long id) {
        return staffAccountRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Data with this id: " + id + " is not found"));
    }

    /**
     *
     * @param staffAccount
     * @return
     */
     public StaffAccount createAccount(StaffAccount staffAccount){
         try{
             staffAccountRepository.save(staffAccount);
             return staffAccount;
         }catch (Exception ex){
             System.out.println(ex);
         }

        return null;
     }

    /**
     * @param pageable
     * @return
     */
    public Page<StaffAccount> getAllStaffPaging(Pageable pageable) {
        try {
            return staffAccountRepository.findAll(pageable);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return null;
    }

    /**
     * @param id
     * @param idRole
     */
    public void modifyRoleOrStatus(Long id, Long idRole) {
        StaffAccount staffAccount = this.findById(id);
        if (staffAccount != null) {
            System.out.println(staffAccount.toString());
            if (idRole == null) {
                if (staffAccount.getAccount_status().getAccount_status().equals("active")) {
                    Account_Status account_status = new Account_Status();
                    account_status.setId((long) 2);
                    staffAccount.setAccount_status(account_status);
                }
            } else {
                Role role = new Role();
                role.setId(idRole);
                staffAccount.setRole(role);

            }
            staffAccountRepository.save(staffAccount);
        }

    }


}
