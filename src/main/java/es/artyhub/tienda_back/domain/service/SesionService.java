package es.artyhub.tienda_back.domain.service;

import java.util.Optional;

import es.artyhub.tienda_back.domain.dto.SesionDto;
import es.artyhub.tienda_back.domain.dto.UserDto;

public interface SesionService {
    SesionDto insertSesion(SesionDto sesionDto);
    void deleteSesion(String token);
    Optional<SesionDto> findSesionByToken(String token);
    UserDto findUserByToken(String token);
}
