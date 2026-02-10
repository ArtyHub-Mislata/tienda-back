package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.persistence.dao.jpa.CartJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CartJpaDaoImpl implements CartJpaDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public CartJpaEntity getCartOfUser(Long idUser) {
        String jpql = """
                SELECT cart
                FROM CartJpaEntity cart
                WHERE cart.user.id = :idUser
                
                """;
        return entityManager
                .createQuery(jpql, CartJpaEntity.class)
                .setParameter("idUser", idUser)
                .setMaxResults(1)
                .getResultList()
                .stream()
                .findFirst()
                .orElse(null);

    }
}
