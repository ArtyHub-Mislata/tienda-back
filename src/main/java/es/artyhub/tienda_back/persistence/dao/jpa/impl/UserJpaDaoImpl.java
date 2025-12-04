package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.persistence.dao.jpa.UserJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

public class UserJpaDaoImpl implements UserJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserJpaEntity> findAll(int page, int size) {
        return null;
    }

    @Override
    public Optional<UserJpaEntity> findById(Long id) {
        return Optional.of(entityManager.find(UserJpaEntity.class, id));
    }

    @Override
    public UserJpaEntity insert(UserJpaEntity jpaEntity) {
        return null;
    }

    @Override
    public UserJpaEntity update(UserJpaEntity jpaEntity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(UserJpaEntity.class, id));
    }

    @Override
    public long count() {
        return 0;
    }
}
