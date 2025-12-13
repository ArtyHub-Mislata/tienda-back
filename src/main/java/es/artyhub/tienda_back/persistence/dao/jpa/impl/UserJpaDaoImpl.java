package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.domain.exception.ResourceNotFoundException;
import es.artyhub.tienda_back.persistence.dao.jpa.UserJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

public class UserJpaDaoImpl implements UserJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserJpaEntity> findAll(int page, int size) {
        int indicePagina = Math.max(page - 1, 0);

        String sql = "SELECT user FROM UserJpaEntity user ORDER BY user.id ASC";

        TypedQuery<UserJpaEntity> userJpaEntityPage =  entityManager
                .createQuery(sql, UserJpaEntity.class)
                .setFirstResult(indicePagina * size)
                .setMaxResults(size);
        return userJpaEntityPage.getResultList();
    }

    @Override
    public Optional<UserJpaEntity> findById(Long id) {
        return Optional.ofNullable(entityManager.find(UserJpaEntity.class, id));
    }

    @Override
    public UserJpaEntity insert(UserJpaEntity jpaEntity) {
        entityManager.persist(jpaEntity);
        return jpaEntity;
    }

    @Override
    public UserJpaEntity update(UserJpaEntity jpaEntity) {
        UserJpaEntity userJpaEntity = entityManager.find(UserJpaEntity.class, jpaEntity.getId());
        if(userJpaEntity == null){
            throw new ResourceNotFoundException("User with id " + jpaEntity.getId() + " not found, error when updating");
        }
        //hacemos la comprobación de si existe ya que si no existe en vez de hacer un update hará in insert
        return entityManager.merge(jpaEntity);

    }

    @Override
    public void deleteById(Long id) {
        entityManager.remove(entityManager.find(UserJpaEntity.class, id));
    }

    @Override
    public long count() {
        String jpql = "SELECT COUNT(a) FROM UserJpaEntity a";
        return entityManager
                .createQuery(jpql, Long.class)
                .getSingleResult();
    }

    @Override
    public UserJpaEntity findByEmail(String email) {
        return entityManager.createQuery(
                        "SELECT u FROM UserJpaEntity u WHERE u.email = :email",
                        UserJpaEntity.class
                )
                .setParameter("email", email)
                .getSingleResult();
    }
}
