package com.fa.DPA.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subcategory")
@Data
public class SubCategory extends AbstractModel {

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private boolean status;

//    @ManyToMany(mappedBy = "subCategories")
//    private List<InteriorDesign> interiorDesigns = new ArrayList<>();

//    @OneToMany(
//            mappedBy = "subCategory"
//    )
//    private List<ConstructionDrawing> constructionDrawings = new ArrayList<>();
}
