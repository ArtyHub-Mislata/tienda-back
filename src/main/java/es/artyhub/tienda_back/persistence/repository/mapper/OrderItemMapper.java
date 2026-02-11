package es.artyhub.tienda_back.persistence.repository.mapper;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.OrderItemDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.OrderItemJpaEntity;

public class OrderItemMapper {
    private static OrderItemMapper instance;

    private OrderItemMapper() {
    }

    public static OrderItemMapper getInstance() {
        if (instance == null) {
            instance = new OrderItemMapper();
        }
        return instance;
    }
    public OrderItemJpaEntity fromOrderItemDtoToOrderItemJpaEntity(OrderItemDto orderItemDto){
        if(orderItemDto == null){
            return null;
        }
        return new OrderItemJpaEntity(
          orderItemDto.getId(),
          orderItemDto.getQuantity(),
          orderItemDto.getPrice(),
          ArtworkMapper.getInstance().fromArtworkDtoToArtworkJpaEntity(orderItemDto.getArtwork())
        );
    }
    public OrderItemDto fromOrderItemJpaEntityToOrderItemDto(OrderItemJpaEntity orderItemJpaEntity){
        if(orderItemJpaEntity == null){
            return null;
        }
        return new OrderItemDto(
                orderItemJpaEntity.getId(),
                orderItemJpaEntity.getQuantity(),
                orderItemJpaEntity.getPrice(),
                ArtworkMapper.getInstance().fromArtworkJpaEntityToArtworkDto(orderItemJpaEntity.getArtworkJpaEntity())
        );
    }
}
