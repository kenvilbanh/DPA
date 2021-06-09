package com.fa.DPA.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class BaseAccount extends AbstractModel {
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false, length = 50)
    private String password;
}
