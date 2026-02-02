package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.ShoppingCartDto;
import es.artyhub.tienda_back.domain.dto.UserDto;

public interface CartRepository {
    ShoppingCartDto getCartOfUser(UserDto userDto);
}
