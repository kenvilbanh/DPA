package com.fa.DPA.model;


import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "subcategory")
@Data
public class SubCategory extends AbstracModel{

    @Column
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToMany(mappedBy = "subCategories")
    private List<InteriorDesign> interiorDesigns = new ArrayList<>();

    @OneToMany(
            mappedBy = "subCategory"
    )
    private List<ConstructionDrawing> constructionDrawings = new ArrayList<>();
}
