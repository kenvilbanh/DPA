package com.fa.DPA.model;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
public class AbstracModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }
}
