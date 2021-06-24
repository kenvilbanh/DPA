package com.fa.DPA.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Data
public class Category extends AbstractModel {

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private Type type;

//    @OneToMany(mappedBy = "category")
//    private List<SubCategory> subcategories = new ArrayList<>();
}
