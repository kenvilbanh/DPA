package com.fa.DPA.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Account_Status extends AbstractModel {
    @Column(unique = true, nullable = false, length = 20)
    private String account_status;
}
