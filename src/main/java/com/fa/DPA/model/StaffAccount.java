package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StaffAccount extends BaseAccount{
    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8")
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
