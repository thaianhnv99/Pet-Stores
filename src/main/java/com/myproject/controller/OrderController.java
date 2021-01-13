package com.myproject.controller;

import com.myproject.business.OrderBusiness;
import com.myproject.common.dto.Datatable;
import com.myproject.common.dto.ResultInsideDTO;
import com.myproject.data.dto.OrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "/mapi/orderController")
public class OrderController {
    @Autowired
    OrderBusiness orderBusiness;

    @PostMapping(value = "/getDatatableOrder")
    public ResponseEntity<Datatable> getDatatableOrder(@RequestBody OrdersDTO ordersDTO) {
        Datatable datatable = orderBusiness.getDatatableOrder(ordersDTO);
        return new ResponseEntity<>(datatable, HttpStatus.OK);
    }

    @PostMapping(value = "/getListOrderByMemberId")
    public ResponseEntity<Datatable> findOrderByIdMember(@RequestBody OrdersDTO ordersDTO) {
        Datatable datatable = orderBusiness.findOrderByIdMember(ordersDTO);
        return new ResponseEntity<>(datatable, HttpStatus.OK);
    }

    @GetMapping(value = "/getDetail")
    public ResponseEntity<OrdersDTO> findOrderById(@RequestParam Long petId) {
        OrdersDTO ordersDTO = orderBusiness.findOrderById(petId);
        return new ResponseEntity<>(ordersDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/insert")
    public ResponseEntity<ResultInsideDTO> insertOrder(@RequestBody OrdersDTO ordersDTO) {
        ResultInsideDTO resultInsideDTO = orderBusiness.insertOrder(ordersDTO);
        return new ResponseEntity<>(resultInsideDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/updateOrderInfo")
    public ResponseEntity<ResultInsideDTO> updateOrderInfo(@RequestBody OrdersDTO ordersDTO) {
        ResultInsideDTO resultInsideDTO = orderBusiness.updateOrderInfo(ordersDTO);
        return new ResponseEntity<>(resultInsideDTO, HttpStatus.OK);
    }

    @PostMapping(value = "/approverOrderInfo")
    public ResponseEntity<ResultInsideDTO> approverOrderInfo(@RequestBody OrdersDTO ordersDTO) {
        ResultInsideDTO resultInsideDTO = orderBusiness.aproverOrderInfo(ordersDTO);
        return new ResponseEntity<>(resultInsideDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/delete")
    public ResponseEntity<ResultInsideDTO> deleteOrderById(@RequestParam Long orderId) {
        ResultInsideDTO resultInsideDTO = orderBusiness.deleteOrderById(orderId);
        return new ResponseEntity<>(resultInsideDTO, HttpStatus.OK);
    }
}
