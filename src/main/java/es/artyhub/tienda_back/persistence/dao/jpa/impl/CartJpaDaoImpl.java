package es.artyhub.tienda_back.persistence.dao.jpa.impl;

import es.artyhub.tienda_back.domain.exception.ResourceNotFoundException;
import es.artyhub.tienda_back.persistence.dao.jpa.CartJpaDao;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartItemJpaEntity;
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

    @Override
    public CartJpaEntity updateCart(CartJpaEntity cartJpaEntityUpdated) {
        CartJpaEntity cartJpaEntity = entityManager.find(CartJpaEntity.class, cartJpaEntityUpdated.getId());
        if(cartJpaEntity == null){
            throw new ResourceNotFoundException("No se ha encontrado el cart que se busca actualizar");
        }
        return entityManager.merge(cartJpaEntityUpdated);
    }

    @Override
    public void clearCart(Long cartId) {
        CartJpaEntity cart = entityManager.find(CartJpaEntity.class, cartId);

        if (cart != null) {
            // Eliminamos todos los items del carrito
            String jpql = """
                    DELETE FROM CartItemJpaEntity ci
                    WHERE ci.cart.id = :cartId
                    """;

            entityManager
                .createQuery(jpql)
                .setParameter("cartId", cartId)
                .executeUpdate();

            // Limpiamos la colección en memoria
            cart.getCartItems().clear();
        }
    }

    @Override
    public CartItemJpaEntity addItemToCart(CartJpaEntity cartJpaEntity, Long idArtwork) {
        ArtworkJpaEntity artworkJpa = entityManager.find(ArtworkJpaEntity.class, idArtwork);
        if(artworkJpa == null){
            throw new ResourceNotFoundException("No se puede añadir un item al carrito que no existe");
        }
        CartItemJpaEntity itemExistente = findCartItem(cartJpaEntity.getId(), artworkJpa.getId());

        //Si estamos añadiendo un cartItem que ya existe, le sumamos uno a s
        if(itemExistente != null){
            if(artworkJpa.getStock() > itemExistente.getQuantity()) {
                itemExistente.setQuantity(itemExistente.getQuantity() + 1);
            }
            return itemExistente;
        } else {
            CartItemJpaEntity cartItemJpaEntity = new CartItemJpaEntity();
            cartItemJpaEntity.setQuantity(1L);
            cartItemJpaEntity.setCart(cartJpaEntity);
            cartItemJpaEntity.setArtworkJpaEntity(artworkJpa);
            entityManager.persist(cartItemJpaEntity);
            return cartItemJpaEntity;
        }

    }
    private CartItemJpaEntity findCartItem(Long cartId, Long artworkId) {
        try {
            String jpql = """
            SELECT ci 
            FROM CartItemJpaEntity ci 
            WHERE ci.cart.id = :cartId 
            AND ci.artworkJpaEntity.id = :artworkId
            """;

            return entityManager
                    .createQuery(jpql, CartItemJpaEntity.class)
                    .setParameter("cartId", cartId)
                    .setParameter("artworkId", artworkId)
                    .setMaxResults(1)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);
        } catch (Exception e) {
            return null;
        }
    }
}
