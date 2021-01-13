package com.myproject.repository;

import com.myproject.common.dto.Datatable;
import com.myproject.common.dto.ResultInsideDTO;
import com.myproject.data.dto.OrdersDTO;

public interface OrderRepository {
    OrdersDTO findOrderById(Long orderId);

    Datatable findOrderByIdMember(OrdersDTO ordersDTO);

    ResultInsideDTO insertOrder(OrdersDTO ordersDTO);

    ResultInsideDTO updateOrderInfo(OrdersDTO ordersDTO);

    ResultInsideDTO deleteOrderById(Long orderId);

    Datatable getDatatableOrder(OrdersDTO ordersDTO);
}
