package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.domain.model.Page;
import es.artyhub.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;

import java.util.List;
import java.util.Optional;

public class CategoryJpaDaoImpl implements CategoryJpaDao {
    @Override
    public Page<List<CategoryJpaEntity>> findAll(int page, int size) {
        return null;
    }

    @Override
    public Optional<CategoryJpaEntity> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public CategoryJpaEntity insert(CategoryJpaEntity jpaEntity) {
        return null;
    }

    @Override
    public CategoryJpaEntity update(CategoryJpaEntity jpaEntity) {
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
