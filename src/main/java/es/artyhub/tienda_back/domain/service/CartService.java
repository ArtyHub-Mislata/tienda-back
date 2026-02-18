package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.dto.CartItemDto;

public interface CartService {
    CartDto getCartOfUser(Long id);
    CartDto updateCart(CartDto cartDto);
    void vaciarCarrito(Long id);
    CartItemDto addItemToCart(CartDto cart, Long artworkId);
}
