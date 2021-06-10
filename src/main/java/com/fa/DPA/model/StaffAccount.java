package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class StaffAccount extends BaseAccount{
    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8")
    private String staff_name;
    @Column(unique = true, length = 50)
    private String email;
    @Column(unique = true, length = 50)
    private String phone;
    @CreatedDate
    private LocalDate create_date;
    private LocalDate end_date;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @ManyToOne
    @JoinColumn(name = "account_status_id")
    private Account_Status account_status;
}
