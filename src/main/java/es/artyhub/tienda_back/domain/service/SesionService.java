package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.SesionDto;

public interface SesionService {
    SesionDto insertSesion(SesionDto sesionDto);
    void deleteSesion(String token);
    SesionDto findSesionByToken(String token);
}
