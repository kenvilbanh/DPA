package com.fa.DPA.model;

import lombok.Data;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Entity
@Data
public class StaffAccount extends BaseAccount{
    private String staff_name;
    @Column(unique = true, length = 50)
    private String email;
    @Column(unique = true, length = 50)
    private String phone;
    private LocalDate create_date;
    private LocalDate end_date;
    @OneToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToOne
    @JoinColumn(name = "account_status_id")
    private Account_Status account_status;
}
