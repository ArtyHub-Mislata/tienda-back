package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.ShoppingCartDto;
import es.artyhub.tienda_back.domain.dto.UserDto;

public interface CartService {
    ShoppingCartDto getCartOfUser(UserDto userDto);
}
