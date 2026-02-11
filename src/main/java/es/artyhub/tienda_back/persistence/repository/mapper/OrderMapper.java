package es.artyhub.tienda_back.persistence.repository.mapper;

import es.artyhub.tienda_back.domain.dto.OrderDto;
import es.artyhub.tienda_back.domain.dto.OrderItemDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.OrderItemJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.OrderJpaEntity;

public class OrderMapper {
    private static OrderMapper instance;

    private OrderMapper() {
    }

    public static OrderMapper getInstance() {
        if (instance == null) {
            instance = new OrderMapper();
        }
        return instance;
    }
    public OrderDto fromOrderJpaEntityToOrderDto(OrderJpaEntity orderJpaEntity){
        if(orderJpaEntity == null){
            return null;
        }
        return new OrderDto(
                orderJpaEntity.getId(),
                orderJpaEntity.getOrderItems()
                        .stream()
                        .map(OrderItemMapper.getInstance():: fromOrderItemJpaEntityToOrderItemDto)
                        .toList(),
                UserMapper.getInstance().fromUserJpaEntityToUserDto(orderJpaEntity.getUser())

        );
    }
    public OrderJpaEntity fromOrderDtoToOrderJpaEntity(OrderDto orderDto){
        if(orderDto == null){
            return null;
        }
        return new OrderJpaEntity(
                orderDto.getId(),
                orderDto.getOrderItems()
                        .stream()
                        .map(OrderItemMapper.getInstance()::fromOrderItemDtoToOrderItemJpaEntity)
                        .toList(),
                UserMapper.getInstance().fromUserDtoToUserJpaEntity(orderDto.getUser())
        );
    }
}
