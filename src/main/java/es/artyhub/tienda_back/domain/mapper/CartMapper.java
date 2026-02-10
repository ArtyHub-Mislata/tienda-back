package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Cart;

public class CartMapper {
    private static CartMapper instance;

    public CartMapper() {
    }

    public static CartMapper getInstance() {
        if (instance == null) {
            instance = new CartMapper();
        }
        return instance;
    }

    public Cart fromCartDtoToCart(CartDto cartDto) {
        if (cartDto == null) {
            throw new BusinessException("CartDto cannot be null");
        }
        return new Cart(
                cartDto.getId(),
                cartDto.getCartItems()
                        .stream()
                        .map(CartItemMapper.getInstance()::fromCartItemDtoToCartItem)
                        .toList(),
                UserMapper.getInstance().fromUserDtoToUser(cartDto.getUserDto())
        );
    }

    public CartDto fromCartToCartDto(Cart cart) {
        if (cart == null) {
            throw new BusinessException("Cart cannot be null");
        }
        return new CartDto(
                cart.getId(),
                cart.getCartItems()
                        .stream()
                        .map(CartItemMapper.getInstance()::fromCartItemToCartItemDto)
                        .toList(),
                UserMapper.getInstance().fromUserToUserDto(cart.getUser())
        );
    }
}
