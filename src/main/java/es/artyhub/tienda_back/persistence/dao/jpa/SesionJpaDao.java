package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;

public interface SesionJpaDao {
    public SesionJpaEntity findByToken(String token);
    public void deleteSesion(String token);
    public void insertSesion(SesionJpaEntity sesion);
}
