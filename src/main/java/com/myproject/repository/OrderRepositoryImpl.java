package com.myproject.repository;

import com.myproject.common.Constant;
import com.myproject.common.dto.BaseDTO;
import com.myproject.common.dto.Datatable;
import com.myproject.common.dto.ResultInsideDTO;
import com.myproject.common.repository.BaseRepository;
import com.myproject.common.utils.DataUtil;
import com.myproject.data.dto.OrdersDTO;
import com.myproject.data.entity.OrdersEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Transactional
@Repository
public class OrderRepositoryImpl extends BaseRepository implements OrderRepository {
    @Override
    public OrdersDTO findOrderById(Long orderId) {
        OrdersEntity ordersEntity = getEntityManager().find(OrdersEntity.class, orderId);
        OrdersDTO ordersDTO = ordersEntity.toDto();
        return ordersDTO;
    }

    @Override
    public Datatable findOrderByIdMember(OrdersDTO ordersDTO) {
        BaseDTO baseDTO = new BaseDTO();
        Map<String, Object> parameter = new HashMap<>();
        String sql = getSQLFromFile("order", "getDatatableOrder");
        if (!DataUtil.isNullOrEmpty(ordersDTO.getUserId())) {
            sql += " And p.idkhachhang = :idkhachhang ";
            parameter.put("idkhachhang", ordersDTO.getUserId());
        } else {
            return null;
        }
        sql += " ORDER BY p.iddonnhannuoi ASC ";
        baseDTO.setSqlQuery(sql);
        baseDTO.setParameters(parameter);
        return getListDataTableBySqlQuery(baseDTO.getSqlQuery(),
                baseDTO.getParameters(), ordersDTO.getPage(), ordersDTO.getPageSize(),
                OrdersDTO.class,
                ordersDTO.getSortName(), ordersDTO.getSortType());
    }

    @Override
    public ResultInsideDTO insertOrder(OrdersDTO ordersDTO) {
        ResultInsideDTO resultInsideDTO = new ResultInsideDTO();
        resultInsideDTO.setKey(Constant.RESPONSE_KEY.SUCCESS);
        OrdersEntity ordersEntity = getEntityManager().merge(ordersDTO.toEntity());
        resultInsideDTO.setId(ordersEntity.getOrderId());
        return resultInsideDTO;
    }

    @Override
    public ResultInsideDTO updateOrderInfo(OrdersDTO ordersDTO) {
        ResultInsideDTO resultInsideDTO = new ResultInsideDTO();
        resultInsideDTO.setKey(Constant.RESPONSE_KEY.SUCCESS);
        OrdersEntity ordersEntity = getEntityManager().find(OrdersEntity.class, ordersDTO.getOrderId());
        if (ordersEntity != null) {
            ordersEntity = getEntityManager().merge(ordersDTO.toEntity());
            resultInsideDTO.setId(ordersEntity.getOrderId());
        } else {
            resultInsideDTO.setKey(Constant.RESPONSE_KEY.RECORD_NOT_EXIST);
        }
        return resultInsideDTO;
    }

    @Override
    public ResultInsideDTO deleteOrderById(Long orderId) {
        ResultInsideDTO resultInsideDTO = new ResultInsideDTO();
        resultInsideDTO.setKey(Constant.RESPONSE_KEY.SUCCESS);
        OrdersEntity ordersEntity = getEntityManager().find(OrdersEntity.class, orderId);
        getEntityManager().remove(ordersEntity);
        return resultInsideDTO;
    }

    @Override
    public Datatable getDatatableOrder(OrdersDTO ordersDTO) {
        BaseDTO baseDTO = sqlSearch(ordersDTO);
        return getListDataTableBySqlQuery(baseDTO.getSqlQuery(),
                baseDTO.getParameters(), ordersDTO.getPage(), ordersDTO.getPageSize(),
                OrdersDTO.class,
                ordersDTO.getSortName(), ordersDTO.getSortType());
    }

    private BaseDTO sqlSearch(OrdersDTO ordersDTO) {
        BaseDTO baseDTO = new BaseDTO();
        Map<String, Object> parameter = new HashMap<>();
        String sql = getSQLFromFile("order", "getDatatableOrder");
//        if (orderDTO != null) {
//            if (!DataUtil.isNullOrEmpty(orderDTO.getSearchAll())) {
//                sql += " And lower(p.tieude) Like lower(:searchAll) ";
//                parameter.put("searchAll", DataUtil.convertSqlLike(orderDTO.getSearchAll()));
//            }
//        }
        sql += " ORDER BY p.iddonnhannuoi ASC ";
        baseDTO.setSqlQuery(sql);
        baseDTO.setParameters(parameter);
        return baseDTO;
    }
}
