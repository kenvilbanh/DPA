package com.fa.DPA.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
public class Order extends AbstractModel {


    //private CustomerAccount owner;

    @ManyToMany
    @JoinTable(name ="cd_order", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "cd_id"))
    private List<ConstructionDrawing> constructionDrawings;

    //private StaffAccount staffAccount;

    @Column
    private String orderCode;

    @Column
    private String note;

//    @ManyToOne
//    @JoinColumn(name ="status_id")
//    private Status status;

    @Column
    private Date createdDate;

    @Column
    private Date confirmedDate;

    @Column
    private Date finishedDate;
}
