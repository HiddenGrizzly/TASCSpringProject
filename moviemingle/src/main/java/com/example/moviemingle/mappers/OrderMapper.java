package com.example.moviemingle.mappers;

import com.example.moviemingle.dtos.order.OrderDTO;
import com.example.moviemingle.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.AfterMapping;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "userName")
//    @Mapping(source = "payment.id", target = "paymentId")
    @Mapping(source = "createdAt", target = "createdAt")
    OrderDTO orderToOrderDTO(Order order);

    @AfterMapping
    default void setTotalPrice(Order order, @MappingTarget OrderDTO orderDTO) {
        order.setTotalPrice();
        orderDTO.setTotalPrice(order.getTotalPrice());
    }

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "userName", target = "user.username")
//    @Mapping(source = "paymentId", target = "payment.id")
    Order orderDTOToOrder(OrderDTO orderDTO);
}
