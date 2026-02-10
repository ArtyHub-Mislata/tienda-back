package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.CartDto;

public interface CartRepository {
    CartDto getCartOfUser(Long idUser);
}
