package com.fa.DPA.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@EntityListeners({AuditingEntityListener.class})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AbstractModel {


    //private CustomerAccount owner;

    @ManyToMany
    @JoinTable(name ="cd_order", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "cd_id"))
    private List<ConstructionDrawing> constructionDrawings;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private CustomerAccount owner;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private StaffAccount staffAccount;

    @Column
    private String orderCode;

    @Column
    private String note;

//    @ManyToOne
//    @JoinColumn(name ="status_id")
//    private Status status;

    @Column
    @CreatedDate
    private Date createdDate;

    @Column
    private Date confirmedDate;

    @Column
    private Date finishedDate;

    @Column
    private String totalPrice;

    @Column(length = 30)
    private String phoneReceive;

    @Column(length = 50)
    private String addressReceive;
}
