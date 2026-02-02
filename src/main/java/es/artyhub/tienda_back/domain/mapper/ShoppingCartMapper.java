package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.ShoppingCartDto;
import es.artyhub.tienda_back.domain.model.ShoppingCart;

public class ShoppingCartMapper {
    private static ShoppingCartMapper instance;

    public ShoppingCartMapper() {
    }

    public static ShoppingCartMapper getInstance() {
        if (instance == null) {
            instance = new ShoppingCartMapper();
        }
        return instance;
    }

    public ShoppingCart fromShoppingCartDtoToShoppingCart(ShoppingCartDto shoppingCartDto) {
        if (shoppingCartDto == null) {
            return null;
        }
        return new ShoppingCart(
            shoppingCartDto.getId(),
            shoppingCartDto.getDetails().stream().map(CartItemMapper.getInstance()::fromDetailDtoToDetail).toList()
        );
    }

    public ShoppingCartDto fromShoppingCartToShoppingCartDto(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            return null;
        }
        return new ShoppingCartDto(
            shoppingCart.getId(),
            shoppingCart.getDetails().stream().map(CartItemMapper.getInstance()::fromDetailToDetailDto).toList()

        );
    }
}
