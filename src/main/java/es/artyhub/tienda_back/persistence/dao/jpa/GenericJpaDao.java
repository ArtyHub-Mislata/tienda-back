package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.domain.model.Page;

import java.util.List;
import java.util.Optional;

public interface GenericJpaDao<T> {
    List<T> findAll(int page, int size);
    Optional<T> findById(Long id);
    T insert(T jpaEntity);
    T update(T jpaEntity);
    void deleteById(Long id);
    long count();
}
