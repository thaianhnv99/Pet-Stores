package com.myproject.business;

import com.myproject.common.dto.Datatable;
import com.myproject.common.dto.ResultInsideDTO;
import com.myproject.data.dto.OrdersDTO;

import java.io.File;

public interface OrderBusiness {
    OrdersDTO findOrderById(Long orderId);

    Datatable findOrderByIdMember(OrdersDTO ordersDTO);

    ResultInsideDTO insertOrder(OrdersDTO ordersDTO);

    ResultInsideDTO updateOrderInfo(OrdersDTO ordersDTO);

    ResultInsideDTO aproverOrderInfo(OrdersDTO ordersDTO);

    ResultInsideDTO deleteOrderById(Long orderId);

    Datatable getDatatableOrder(OrdersDTO ordersDTO);

    File exportData(OrdersDTO ordersDTO) throws Exception;
}
