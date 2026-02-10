package es.artyhub.tienda_back.persistence.repository.mapper;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartItemJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartJpaEntity;

import java.util.List;

public class CartMapper {
    private static CartMapper instance;

    private CartMapper() {
    }

    public static CartMapper getInstance() {
        if (instance == null) {
            instance = new CartMapper();
        }
        return instance;
    }

    public CartDto fromCartJpaEntityToCartDto(CartJpaEntity cartJpaEntity) {
        if (cartJpaEntity == null) {
            return null;
        }
        return new CartDto(
                cartJpaEntity.getId(),
                cartJpaEntity.getCartItems()
                        .stream()
                        .map(CartItemMapper.getInstance()::fromCartItemJpaEntityToCartItemDto)
                        .toList(),
                UserMapper.getInstance().fromUserJpaEntityToUserDto(cartJpaEntity.getUser())


        );
    }

    public CartJpaEntity fromCartDtoToCartJpaEntity(CartDto cartDto) {
        if (cartDto == null) {
            return null;
        }

        List<CartItemJpaEntity> cartItemJpaEntityList =  cartDto.getCartItems()
                .stream()
                .map(CartItemMapper.getInstance()::fromCartItemDtoToCartItemJpaEntity)
                .toList();



        CartJpaEntity cartJpaEntity =  new CartJpaEntity(
                cartDto.getId(),
                cartItemJpaEntityList,
                UserMapper.getInstance()
                        .fromUserDtoToUserJpaEntity(cartDto.getUserDto())
                );

        cartJpaEntity
                .getCartItems()
                .forEach(cartItemJpaEntity -> cartItemJpaEntity.setCart(cartJpaEntity));

        return cartJpaEntity;
    }
}
