package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.CartDto;

public interface CartService {
    CartDto getCartOfUser(Long id);
}
