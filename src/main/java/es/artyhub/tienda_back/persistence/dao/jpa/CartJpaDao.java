package es.artyhub.tienda_back.persistence.dao.jpa;

import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartItemJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

public interface CartJpaDao {
    CartJpaEntity getCartOfUser(Long idUser);
    CartJpaEntity updateCart(CartJpaEntity cartJpaEntity);
    void clearCart(Long idCart);
    CartItemJpaEntity addItemToCart(CartJpaEntity cartJpaEntity, Long idArtwork);

}
