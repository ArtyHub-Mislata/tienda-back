package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.SesionDto;
import es.artyhub.tienda_back.domain.dto.UserDto;

import java.util.Optional;

public interface SesionRepository {
    SesionDto insertSesion(SesionDto sesionDto);
    void deleteSesion(String token);
    Optional<SesionDto> findByToken(String token);
    UserDto findUserByToken(String token);
}
