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
    @Column(name="numberfloor")
    private int numberFloor;
    @Column(name = "areasize")
    private float areaSize;
    @Column
    private String description;
    @Column(name = "imgsource")
    private String imgSource;
    @Column
    private float price;
    @Column
    private String thumbnail;

    @OneToMany(
            mappedBy = "constructionDrawing"
    )
    private List<Discount> discounts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name ="subCategory")
    private SubCategory subCategory;
}
