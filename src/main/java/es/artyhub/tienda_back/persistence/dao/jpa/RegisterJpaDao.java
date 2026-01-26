package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.domain.dto.RegisterDto;

public interface RegisterJpaDao {
    void register(RegisterDto registerDto);
}
