package com.fa.DPA.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
public class Status extends AbstractModel {
    @Column(unique = true,length = 20, nullable = false)
    private String status;
}
