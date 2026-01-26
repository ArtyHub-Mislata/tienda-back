package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.RegisterDto;

public interface RegisterRepository {
    void register(RegisterDto registerDto);
}
