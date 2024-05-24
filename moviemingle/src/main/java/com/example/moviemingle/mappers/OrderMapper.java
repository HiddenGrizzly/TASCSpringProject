package com.example.moviemingle.mappers;

import com.example.moviemingle.dto.OrderDTO;
import com.example.moviemingle.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "payment.id", target = "paymentId")
    OrderDTO orderToOrderDTO(Order order);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "paymentId", target = "payment.id")
    Order orderDTOToOrder(OrderDTO orderDTO);
}
