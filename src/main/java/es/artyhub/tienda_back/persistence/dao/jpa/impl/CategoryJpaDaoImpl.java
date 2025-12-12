package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.domain.exception.ResourceNotFoundException;
import es.artyhub.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class CategoryJpaDaoImpl implements CategoryJpaDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<CategoryJpaEntity> findAll(int page, int size) {
        int indicePagina = Math.max(page - 1, 0);

        String sql = "SELECT category FROM CategoryJpaEntity category ORDER BY category.id ASC";

        TypedQuery<CategoryJpaEntity> categoryJpaEntityPage =  entityManager
                .createQuery(sql, CategoryJpaEntity.class)
                .setFirstResult(indicePagina * size)
                .setMaxResults(size);
        return categoryJpaEntityPage.getResultList();
    }

    @Override
    public Optional<CategoryJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(CategoryJpaEntity.class, id));
    }

    @Override
    public CategoryJpaEntity insert(CategoryJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public CategoryJpaEntity update(CategoryJpaEntity jpaEntity) {
        CategoryJpaEntity categoryJpaEntity = entityManager.find(CategoryJpaEntity.class, jpaEntity.getId());
        if(categoryJpaEntity == null){
            throw new ResourceNotFoundException("Category with id " + jpaEntity.getId() + " not found, error when updating");
        }
        //hacemos la comprobación de si existe ya que si no existe en vez de hacer un update hará in insert
        return entityManager.merge(jpaEntity);

    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(CategoryJpaEntity.class, id));
    }

    @Override
    public long count() {
        String jpql = "SELECT COUNT(a) FROM CategoryJpaEntity a";
        return entityManager
                .createQuery(jpql, Long.class)
                .getSingleResult();
    }
}
