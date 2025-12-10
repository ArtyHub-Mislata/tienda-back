package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.domain.dto.SesionDto;
import java.util.Optional;

public interface SesionRepository {
    public void insertSesion(SesionDto sesionDto);
    public void deleteSesion(String token);
    public Optional<SesionDto> findByToken(String token);
}
