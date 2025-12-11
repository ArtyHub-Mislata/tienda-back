package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;
import java.util.Optional;

public interface SesionJpaDao {
    public Optional<SesionJpaEntity> findByToken(String token);
    public void deleteSesion(String token);
    public void insertSesion(SesionJpaEntity sesion);
}
