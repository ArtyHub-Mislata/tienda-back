package es.artyhub.tienda_back.domain.service.impl;

import es.artyhub.tienda_back.domain.dto.ShoppingCartDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.repository.CartRepository;
import es.artyhub.tienda_back.domain.service.CartService;

public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public ShoppingCartDto getCartOfUser(UserDto userDto) {
        return cartRepository.getCartOfUser(userDto);
    }
}
