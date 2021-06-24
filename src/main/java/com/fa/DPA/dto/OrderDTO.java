package com.fa.DPA.dto;

import com.fa.DPA.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class OrderDTO {
    private Long id;
    private String orderCode;
    private Map<Long, String> productName = new HashMap<>();
    private String totalPrice;
    private String note;
    private Date createDate;
    private Date finishedDate;
    private Long ownerId;
    private long status;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.orderCode = order.getOrderCode();
        for (int i = 0; i < order.getConstructionDrawings().size(); i++) {
            productName.put(order.getConstructionDrawings().get(i).getId(),
                    order.getConstructionDrawings().get(i).getName());
        }
        this.totalPrice = order.getTotalPrice();
        this.createDate = order.getCreatedDate();
        this.finishedDate = order.getFinishedDate();
        this.status = order.getStatus().getId();
        this.ownerId = order.getOwner().getId();
    }
}
