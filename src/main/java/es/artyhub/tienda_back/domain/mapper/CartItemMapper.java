package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.CartItemDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.CartItem;

public class CartItemMapper {
    private static CartItemMapper instance;

    public CartItemMapper() {
    }

    public static CartItemMapper getInstance() {
        if (instance == null) {
            instance = new CartItemMapper();
        }
        return instance;
    }

    public CartItem fromCartItemDtoToCartItem(CartItemDto cartItemDto) {
        if (cartItemDto == null) {
            throw new BusinessException("CartItemDto cannot be null");
        }
        return new CartItem(
                cartItemDto.getId(),
                cartItemDto.getQuantity(),
                CartMapper.getInstance().fromCartDtoToCart(cartItemDto.getCartDto()),
                ArtworkMapper.getInstance().fromArtworkDtoToArtwork(cartItemDto.getArtworkDto())
        );
    }

    public CartItemDto fromCartItemToCartItemDto(CartItem cartItem) {
        if (cartItem == null) {
            throw new BusinessException("CartItem cannot be null");
        }
        return new CartItemDto(
            cartItem.getId(),
            cartItem.getQuantity(),
            CartMapper.getInstance().fromCartToCartDto(cartItem.getCart()),
            ArtworkMapper.getInstance().fromArtworkToArtworkDto(cartItem.getArtwork())
        );
    }
}
