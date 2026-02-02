package es.artyhub.tienda_back.persistence.repository.mapper;

import es.artyhub.tienda_back.domain.dto.ShoppingCartDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartJpaEntity;

public class ShoppingCartMapper {
    private static ShoppingCartMapper instance;

    private ShoppingCartMapper() {
    }

    public static SesionMapper getInstance() {
        if (instance == null) {
            instance = new ShoppingCartMapper();
        }
        return instance;
    }
    public ShoppingCartDto fromShoppingCartEntityToShoppingCartDto(CartJpaEntity cart){
        if(cart = null){
            return null;
        }
        return new ShoppingCartDto(
                cart.getId(),

        )
    }
}
