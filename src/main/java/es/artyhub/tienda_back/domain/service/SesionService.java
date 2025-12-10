package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.domain.dto.SesionDto;

public interface SesionService {
    public void insertSesion(SesionDto sesionDto);
    public void deleteSesion(String token);
    public boolean isValidToken(String token);
}
