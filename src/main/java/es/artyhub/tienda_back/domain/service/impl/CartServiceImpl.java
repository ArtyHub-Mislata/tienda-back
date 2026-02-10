package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.dto.CartItemDto;
import es.artyhub.tienda_back.domain.repository.CartRepository;
import es.artyhub.tienda_back.domain.service.CartService;
import jakarta.transaction.Transactional;

public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartDto getCartOfUser(Long id) {
        return cartRepository.getCartOfUser(id);
    }

    @Transactional
    @Override
    public CartDto updateCart(CartDto cartDto) {
        return cartRepository.saveCart(cartDto);
    }

    @Transactional
    @Override
    public void vaciarCarrito(Long id) {
        cartRepository.vaciarCarrito(id);
    }

    @Transactional
    @Override
    public CartItemDto addItemToCart(CartDto cart ,Long artworkId) {
        return cartRepository.addItemToCart(cart, artworkId);
    }
}
