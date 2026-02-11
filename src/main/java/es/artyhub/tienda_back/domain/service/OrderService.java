package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.OrderDto;
import es.artyhub.tienda_back.domain.dto.OrderItemDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> getOrdersOfUser(Long userId);
    OrderDto insertOrder(OrderDto orderDto);
}
