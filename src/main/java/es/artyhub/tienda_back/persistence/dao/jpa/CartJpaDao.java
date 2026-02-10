package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartJpaEntity;

public interface CartJpaDao {
    public CartJpaEntity getCartOfUser(Long idUser);
}
