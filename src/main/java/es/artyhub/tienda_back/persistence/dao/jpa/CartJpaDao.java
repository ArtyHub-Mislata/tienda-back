package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartItemJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

public interface CartJpaDao {
    public CartItemJpaEntity getCartOfUser(Long idUser);
}
