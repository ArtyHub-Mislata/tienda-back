package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.persistence.dao.jpa.OrderJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.OrderJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import java.util.List;

public class OrderJpaDaoImpl implements OrderJpaDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public OrderJpaEntity insertOrder(OrderJpaEntity orderJpaEntity) {
        entityManager.persist(orderJpaEntity);
        return orderJpaEntity;
    }


    @Override
    public List<OrderJpaEntity> findAllOrdersOfUser(Long userId) {
        String jpql = """
        SELECT o
        FROM OrderJpaEntity o
        WHERE o.user.id = :userId
        ORDER BY o.id DESC
        """;

        return entityManager
                .createQuery(jpql, OrderJpaEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
