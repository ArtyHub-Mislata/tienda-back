package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.OrderDto;
import es.artyhub.tienda_back.domain.repository.OrderRepository;
import es.artyhub.tienda_back.domain.service.OrderService;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderDto> getOrdersOfUser(Long userId) {
        return orderRepository.getOrdersOfUser(userId);
    }

    @Override
    public OrderDto insertOrder(OrderDto orderDto) {
        return orderRepository.insertOrder(orderDto);
    }
}
