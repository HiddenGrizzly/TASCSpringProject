package com.example.moviemingle.mappers;

import com.example.moviemingle.dtos.order.OrderDetailDTO;
import com.example.moviemingle.entities.OrderDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderDetailMapper {
    OrderDetailMapper INSTANCE = Mappers.getMapper(OrderDetailMapper.class);

    @Mappings({
            @Mapping(source = "order.id", target = "orderId"),
            @Mapping(source = "movie.id", target = "movieId"),
            @Mapping(source = "movie.movieTitle", target = "movieTitle"),
            @Mapping(source = "movie.price", target = "moviePrice")
    })
    OrderDetailDTO orderDetailToOrderDetailDTO(OrderDetail orderDetail);

    @Mappings({
            @Mapping(source = "orderId", target = "order.id"),
            @Mapping(source = "movieId", target = "movie.id"),
            @Mapping(source = "movieTitle", target = "movie.movieTitle"),
            @Mapping(source = "moviePrice", target = "movie.price")
    })
    OrderDetail orderDetailDTOToOrderDetail(OrderDetailDTO orderDetailDTO);

}
