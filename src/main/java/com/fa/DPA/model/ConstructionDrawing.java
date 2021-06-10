package com.fa.DPA.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "construction_drawing")
public class ConstructionDrawing extends AbstracModel {
    @Column
    private String name;
    @Column
    private String code;
    @Column
    private int numberFloor;
    @Column
    private float areaSize;
    @Column
    private String description;
    @Column
    private String imgSource;
    @Column
    private float price;
    @Column
    private String thumbnail;

    @OneToMany(
            mappedBy = "constructionDrawing"
    )
    private List<Discount> discounts = new ArrayList<>();

    @ManyToMany(
            mappedBy = "constructionDrawings"
    )
    private List<Order> orders = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name ="subCategory")
    private SubCategory subCategory;
}
