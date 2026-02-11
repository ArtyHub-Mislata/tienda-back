package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.dto.OrderDto;
import es.artyhub.tienda_back.domain.repository.OrderRepository;
import es.artyhub.tienda_back.persistence.dao.jpa.OrderJpaDao;
import es.artyhub.tienda_back.persistence.repository.mapper.OrderMapper;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    private final OrderJpaDao orderJpaDao;

    public OrderRepositoryImpl(OrderJpaDao orderJpaDao) {
        this.orderJpaDao = orderJpaDao;
    }

    @Override
    public List<OrderDto> getOrdersOfUser(Long idUser) {
        return orderJpaDao.findAllOrdersOfUser(idUser)
                .stream()
                .map(OrderMapper.getInstance()::fromOrderJpaEntityToOrderDto)
                .toList();
    }

    @Override
    public OrderDto insertOrder(OrderDto orderDto) {
        return OrderMapper.getInstance()
                .fromOrderJpaEntityToOrderDto(
                        orderJpaDao.insertOrder(
                                OrderMapper.getInstance().fromOrderDtoToOrderJpaEntity(orderDto)
                        )
                );
    }
}
