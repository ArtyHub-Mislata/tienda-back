package es.artyhub.tienda_back.domain.service;

import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;

public interface SesionService {
    public void insertSesion(SesionJpaEntity sesionJpaEntity);
    public void deleteSesion(String token);
    public boolean isValidToken(String token);
}
