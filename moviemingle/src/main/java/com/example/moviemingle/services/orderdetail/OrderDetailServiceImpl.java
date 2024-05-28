package com.example.moviemingle.services.orderdetail;

import com.example.moviemingle.dtos.order.OrderDetailDTO;
import com.example.moviemingle.entities.OrderDetail;
import com.example.moviemingle.exceptions.OrderNotFoundException;
import com.example.moviemingle.mappers.OrderDetailMapper;
import com.example.moviemingle.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Override
    public List<OrderDetailDTO> getAllOrderDetail(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return orderDetailRepository.findAll(pageable).stream().map(orderDetailMapper::orderDetailToOrderDetailDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDetailDTO getOrderDetailById(Long orderDetailId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderDetailId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderDetailId + " not found"));
        return orderDetailMapper.orderDetailToOrderDetailDTO(orderDetail);
    }
    @Override
    public void addOrderDetail(OrderDetailDTO orderDetailDTO) {
        OrderDetail orderDetail = orderDetailMapper.orderDetailDTOToOrderDetail(orderDetailDTO);
        orderDetailRepository.save(orderDetail);
    }
    @Override
    public void updateOrderDetail(Long orderDetailId, OrderDetailDTO orderDetailDTO) {
        OrderDetail existingOrderDetail = orderDetailRepository.findById(orderDetailId).get();
        if (existingOrderDetail != null) {
            existingOrderDetail = orderDetailMapper.orderDetailDTOToOrderDetail(orderDetailDTO);
            orderDetailRepository.save(existingOrderDetail);
        }
    }
    @Override
    public void deleteOrderDetail(Long orderDetailId) {
        orderDetailRepository.deleteById(orderDetailId);
    }
}