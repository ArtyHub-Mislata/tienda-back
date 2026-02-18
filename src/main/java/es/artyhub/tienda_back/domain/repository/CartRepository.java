package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.dto.CartItemDto;

public interface CartRepository {

    CartDto getCartOfUser(Long idUser);

    CartDto saveCart(CartDto cartDto);

    void vaciarCarrito(Long id);
    
    CartItemDto addItemToCart(CartDto cartDto, Long idArtwork);
}
