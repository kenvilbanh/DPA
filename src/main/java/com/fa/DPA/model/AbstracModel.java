package com.fa.DPA.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class AbstracModel implements Serializable {
    @Id
    private Long id;
}
