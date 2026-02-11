package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.OrderDto;

import java.util.List;

public interface OrderRepository {
    List<OrderDto> getOrdersOfUser(Long idUser);
    OrderDto insertOrder(OrderDto orderDto);
}
