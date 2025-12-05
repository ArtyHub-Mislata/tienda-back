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
            shoppingCartDto.findUserById(shoppingCartDto.getUserId()),
            shoppingCartDto.getDetails().stream().map(DetailMapper.getInstance()::fromDetailDtoToDetail).toList(),
            PayMethodMapper.getInstance().fromPayMethodDtoToPayMethod(shoppingCartDto.getPayMethodDto()),
            shoppingCartDto.getTotal(),
            shoppingCartDto.getState()
        );
    }

    public ShoppingCartDto fromShoppingCartToShoppingCartDto(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            return null;
        }
        return new ShoppingCartDto(
            shoppingCart.getId(),
            shoppingCart.getUser().getId(),
            shoppingCart.getDetails().stream().map(DetailMapper.getInstance()::fromDetailToDetailDto).toList(),
            PayMethodMapper.getInstance().fromPayMethodToPayMethodDto(shoppingCart.getPayMethod()),
            shoppingCart.getTotal(),
            shoppingCart.getState()
        );
    }
}
