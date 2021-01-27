package com.myproject.data.dto;

import com.myproject.common.dto.BaseDTO;
import com.myproject.data.entity.OrdersEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredTypes()
public class OrdersDTO extends BaseDTO {

    private Long orderId;
    private Long userId;
    private Long managerId;
    private Long petId;
    private String reason;
    private String conditions;
    private Date sentDate;
    private Date approvalDate;
    private Date deliveryDate;
    private String status;
    private String petName;
    private String name;
    private String phone;
    private String address;

    private String statusStr;

    public OrdersDTO(Long orderId,
                     Long userId,
                     Long managerId,
                     Long petId,
                     String reason,
                     String condition,
                     Date sentDate,
                     Date approvalDate,
                     Date deliveryDate,
                     String status) {
        this.orderId = orderId;
        this.userId = userId;
        this.managerId = managerId;
        this.petId = petId;
        this.reason = reason;
        this.conditions = condition;
        this.sentDate = sentDate;
        this.approvalDate = approvalDate;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    public OrdersEntity toEntity() {
        return new OrdersEntity(
                orderId,
                userId,
                managerId,
                petId,
                reason,
                conditions,
                sentDate,
                approvalDate,
                deliveryDate,
                status
        );
    }
}
