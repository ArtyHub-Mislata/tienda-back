package es.artyhub.tienda_back.domain.repository;

import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;

public interface SesionRepository {
    public void insertSesion(SesionJpaEntity sesionJpaEntity);
    public void deleteSesion(String token);
    public SesionJpaEntity findByToken(String token);
}
