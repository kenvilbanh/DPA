package com.fa.DPA.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
@Data
public class CustomerContact extends AbstractModel{
    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8")
    private String customer_name;
    @Column(length = 50)
    private String phone;
    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8")
    private String area_size;
    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8")
    private String wanted_style;
    @Column(columnDefinition = "VARCHAR(100) CHARSET utf8")
    private String address;
    @OneToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
