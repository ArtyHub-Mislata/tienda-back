package es.artyhub.tienda_back.persistence.repository.mapper;

import es.artyhub.tienda_back.domain.dto.CartItemDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartItemJpaEntity;

public class CartItemMapper {
    private static CartItemMapper instance;

    private CartItemMapper() {
    }

    public static CartItemMapper getInstance() {
        if (instance == null) {
            instance = new CartItemMapper();
        }
        return instance;
    }

    public CartItemJpaEntity fromCartItemDtoToCartItemJpaEntity(CartItemDto cartItemDto) {
        if (cartItemDto == null) {
            return null;
        }
        return new CartItemJpaEntity(
                cartItemDto.getId(),
                cartItemDto.getQuantity(),
                ArtworkMapper.getInstance().fromArtworkDtoToArtworkJpaEntity(cartItemDto.getArtwork())
        );
    }

    public CartItemDto fromCartItemJpaEntityToCartItemDto(CartItemJpaEntity cartItemJpaEntity) {
        if (cartItemJpaEntity == null) {
            return null;
        }
        return new CartItemDto(
                cartItemJpaEntity.getId(),
                cartItemJpaEntity.getQuantity(),
                ArtworkMapper.getInstance().fromArtworkJpaEntityToArtworkDto(cartItemJpaEntity.getArtworkJpaEntity())
        );
    }
}
