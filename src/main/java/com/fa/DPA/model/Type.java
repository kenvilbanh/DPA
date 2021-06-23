package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "type")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Type extends AbstractModel{
    @Column
    private String type_name;

    /*@OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "type"
    )*/
    /*private List<Category> categories = new ArrayList<>();*/

    @Override
    public String toString() {
        return "Type{" +
                "type_name='" + type_name + '\'' +
                '}';
    }
}
