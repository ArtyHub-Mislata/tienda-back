package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.persistence.dao.jpa.entity.OrderItemJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.OrderJpaEntity;

import java.util.List;

public interface OrderJpaDao {
    OrderJpaEntity insertOrder(OrderJpaEntity orderJpaEntity);
    List<OrderJpaEntity> findAllOrdersOfUser(Long userId);
}
