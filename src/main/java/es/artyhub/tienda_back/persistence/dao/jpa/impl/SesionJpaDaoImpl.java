package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.persistence.dao.jpa.SesionJpaDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.SesionJpaEntity;
import jakarta.transaction.Transactional;

import java.util.Optional;

public class SesionJpaDaoImpl implements SesionJpaDao {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<SesionJpaEntity> findByToken(String token) {
        return Optional.ofNullable(entityManager.find(SesionJpaEntity.class, token));
    }
    @Transactional
    @Override
    public void deleteSesion(String token) {
        entityManager.remove(entityManager.find(SesionJpaEntity.class, token));
    }

    @Transactional
    @Override
    public void insertSesion(SesionJpaEntity sesion) {
        entityManager.persist(sesion);
    }
}
