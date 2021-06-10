package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role extends AbstractModel{
    @Column(unique = true, length = 20, nullable = false)
    private String name;
    private LocalDate create_date;
    private LocalDate end_date;
}
