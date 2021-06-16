package com.fa.DPA.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@EntityListeners({AuditingEntityListener.class})
@Data
public class Order extends AbstractModel {


    @ManyToOne
    @JoinColumn(name ="customer_id")
    private CustomerAccount customerAccount;

    @ManyToMany
    @JoinTable(name ="cd_order", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "cd_id"))
    private List<ConstructionDrawing> constructionDrawings;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffAccount staffAccount;

    @Column
    private String orderCode;

    @Column
    private String note;



    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    private Date confirmedDate;

    @Column
    private Date finishedDate;

    @Column
    private String totalPrice;
}
