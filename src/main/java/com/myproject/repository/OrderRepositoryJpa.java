package com.myproject.repository;

import com.myproject.data.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface OrderRepositoryJpa extends JpaRepository<OrdersEntity, Long> {
    OrdersEntity findByUserId(Long memberId);

    List<OrdersEntity> findByPetId(Long petId);

    @Modifying
    @Query(value = "update orders o set " +
            "o.trangthai = :status, " +
            "o.ngayduyetnuoi = :approvalDate, " +
            "o.ngaygiaovatnuoi = :deliveryDate " +
            " where o.iddonnhannuoi = :iddonnhan", nativeQuery = true)
    int aproverOrderInfo(@Param("status") String status, @Param("iddonnhan") Long iddonnhan, @Param("approvalDate") Date approvalDate, @Param("deliveryDate") Date deliveryDate);
}
