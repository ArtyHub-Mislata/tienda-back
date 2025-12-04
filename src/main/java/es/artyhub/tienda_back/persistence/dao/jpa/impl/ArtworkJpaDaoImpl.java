package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.persistence.dao.jpa.ArtworkJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;

import java.util.List;
import java.util.Optional;

public class ArtworkJpaDaoImpl implements ArtworkJpaDao {
    @Override
    public Page<List<ArtworkJpaEntity>> findAll(int page, int size) {
        return null;
    }

    @Override
    public Optional<ArtworkJpaEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public ArtworkJpaEntity insert(ArtworkJpaEntity jpaEntity) {
        return null;
    }

    @Override
    public ArtworkJpaEntity update(ArtworkJpaEntity jpaEntity) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public long count() {
        return 0;
    }
}
