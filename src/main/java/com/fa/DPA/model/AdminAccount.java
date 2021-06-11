package com.fa.DPA.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Admin_account")
@Data
@NoArgsConstructor
public class AdminAccount extends BaseAccount{

}
