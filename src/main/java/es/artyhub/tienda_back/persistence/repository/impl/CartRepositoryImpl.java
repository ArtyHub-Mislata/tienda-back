package es.artyhub.tienda_back.persistence.repository.impl;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.dto.CartItemDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.repository.CartRepository;
import es.artyhub.tienda_back.persistence.dao.jpa.CartJpaDao;
import es.artyhub.tienda_back.persistence.repository.mapper.CartMapper;


public class CartRepositoryImpl implements CartRepository {
    private final CartJpaDao cartJpaDao;

    public CartRepositoryImpl(CartJpaDao cartJpaDao) {
        this.cartJpaDao = cartJpaDao;
    }

    @Override
    public CartDto getCartOfUser(Long idUser) {
        return CartMapper.getInstance().fromCartEntityToShoppingCartDto(cartJpaDao.getCartOfUser(idUser));
    }

    @Override
    public CartDto saveCart(CartDto cartDto) {
        return CartMapper.getInstance().fromCartEntityToShoppingCartDto(
                cartJpaDao.updateCart(
                        CartMapper.getInstance().fromCartDtoToCartJpaEntity(cartDto)
                )
        );
    }

    @Override
    public void vaciarCarrito(Long id) {
        cartJpaDao.clearCart(id);
    }

    @Override
    public CartItemDto addItemToCart(CartDto cartDto,Long idArtwork) {
        return cartJpaDao;
    }
}
