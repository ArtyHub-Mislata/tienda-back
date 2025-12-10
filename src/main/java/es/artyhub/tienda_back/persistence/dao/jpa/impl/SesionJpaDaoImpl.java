package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.persistence.dao.jpa.SesionJpaDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;

public class SesionJpaDaoImpl implements SesionJpaDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public SesionJpaEntity findByToken(String token) {
        return entityManager.find(SesionJpaEntity.class, token);
    }

    @Override
    public void deleteSesion(String token) {
        entityManager.remove(entityManager.find(SesionJpaEntity.class, token));
    }

    @Override
    public void insertSesion(SesionJpaEntity sesion) {
        entityManager.persist(sesion);
    }
}
