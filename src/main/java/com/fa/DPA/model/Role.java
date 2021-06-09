package com.fa.DPA.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
public class Role extends AbstractModel{
    @Column(unique = true, length = 20,nullable = false)
    private String name;
    private LocalDate create_date;
    private LocalDate end_date;
}
