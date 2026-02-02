package es.artyhub.tienda_back.persistence.repository.mapper;

import es.artyhub.tienda_back.domain.dto.CartItemDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartJpaEntity;

public class CartItemMapper {
    private static CartItemMapper instance;

    private CartItemMapper() {
    }

    public static SesionMapper getInstance() {
        if (instance == null) {
            instance = new CartItemMapper();
        }
        return instance;
    }
    public CartJpaEntity fromCartItemDtoToCartItemJpaEntity(CartItemDto cartItemDto){
        if(cartItemDto == null){
            return null;
        }
        return new CartJpaEntity(
                cartItemDto.getId(),

        );
    }
}
