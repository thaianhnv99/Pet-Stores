package com.myproject.business;

import com.myproject.common.Constant;
import com.myproject.common.dto.Datatable;
import com.myproject.common.dto.ResultInsideDTO;
import com.myproject.data.dto.OrdersDTO;
import com.myproject.data.entity.OrdersEntity;
import com.myproject.repository.OrderRepository;
import com.myproject.repository.OrderRepositoryJpa;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Slf4j
@Service
public class OrderBusinessImpl implements OrderBusiness {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderRepositoryJpa orderRepositoryJpa;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrdersDTO findOrderById(Long orderId) {
        return orderRepository.findOrderById(orderId);
    }

    @Override
    public Datatable findOrderByIdMember(OrdersDTO ordersDTO) {
        return orderRepository.findOrderByIdMember(ordersDTO);
    }

    @Override
    public ResultInsideDTO insertOrder(OrdersDTO ordersDTO) {
        ResultInsideDTO resultInsideDTO = new ResultInsideDTO();
        try {
            List<OrdersEntity> ordersEntityList = orderRepositoryJpa.findByPetId(ordersDTO.getPetId());
            if (ordersEntityList.size() == 0) {
                resultInsideDTO = orderRepository.insertOrder(ordersDTO);
            } else {
                resultInsideDTO.setKey(Constant.RESPONSE_KEY.ERROR);
                resultInsideDTO.setMessages("Đơn cho thú cưng này đã tồn tại");
                return resultInsideDTO;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return resultInsideDTO;
    }

    @Override
    public ResultInsideDTO updateOrderInfo(OrdersDTO ordersDTO) {
        return orderRepository.updateOrderInfo(ordersDTO);
    }

    @Override
    public ResultInsideDTO aproverOrderInfo(OrdersDTO ordersDTO) {
        ResultInsideDTO resultInsideDTO = new ResultInsideDTO();
        try {
            OrdersDTO orders = orderRepository.findOrderById(ordersDTO.getOrderId());
            if (orders != null) {
                orderRepositoryJpa.aproverOrderInfo(ordersDTO.getStatus(), ordersDTO.getOrderId(), ordersDTO.getApprovalDate(), ordersDTO.getDeliveryDate());
                resultInsideDTO.setKey(Constant.RESPONSE_KEY.SUCCESS);
                resultInsideDTO.setId(orders.getOrderId());
            } else {
                resultInsideDTO.setKey(Constant.RESPONSE_KEY.RECORD_NOT_EXIST);
                return resultInsideDTO;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return resultInsideDTO;
    }

    @Override
    public ResultInsideDTO deleteOrderById(Long orderId) {
        return orderRepository.deleteOrderById(orderId);
    }

    @Override
    public Datatable getDatatableOrder(OrdersDTO ordersDTO) {
        return orderRepository.getDatatableOrder(ordersDTO);
    }

    @Override
    public File exportData(OrdersDTO ordersDTO) throws Exception {
        return null;
    }
}
