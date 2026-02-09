package es.artyhub.tienda_back.domain.mapper;

import es.artyhub.tienda_back.domain.dto.CartItemDto;
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
            return null;
        }
        return new CartItem(
                cartItemDto.getId(),
                cartItemDto.getQuantity(),
                ArtworkMapper.getInstance().fromArtworkDtoToArtwork(cartItemDto.getArtwork())

        );
    }

    public CartItemDto fromCartItemToCartItemDto(CartItem cartItem) {
        if (cartItem == null) {
            return null;
        }
        return new CartItemDto(
            cartItem.getId(),
            cartItem.getQuantity(),
            ArtworkMapper.getInstance().fromArtworkToArtworkDto(cartItem.getArtwork())
        );
    }
}
