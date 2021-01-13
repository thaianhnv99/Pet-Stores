package com.myproject.data.entity;

import com.myproject.data.dto.OrdersDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ORDERS")
public class OrdersEntity implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constant.SEQUENCE_KEY.EMPLOYEE)
//    @SequenceGenerator(name = Constant.SEQUENCE_KEY.EMPLOYEE, sequenceName = "SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddonnhannuoi")
    private Long orderId;

    @Column(name = "idkhachhang")
    private Long userId;

    @Column(name = "idnguoiquantri")
    private Long managerId;

    @Column(name = "idvatnuoi")
    private Long petId;

    @Column(name = "lydo")
    private String reason;

    @Column(name = "khaibaodieukien")
    private String conditions;

    @Column(name = "ngayguidon")
    private Date sentDate;

    @Column(name = "ngayduyetnuoi")
    private Date approvalDate;

    @Column(name = "ngaygiaovatnuoi")
    private Date deliveryDate;

    @Column(name = "trangthai")
    private String status;

    public OrdersDTO toDto() {
        return new OrdersDTO(
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
