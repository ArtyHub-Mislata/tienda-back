package es.artyhub.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private CartJpaEntity cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artwork_id", nullable = false)
    private ArtworkJpaEntity artworkJpaEntity ;

    public CartJpaEntity getCart() {
        return cart;
    }

    public void setCart(CartJpaEntity cart) {
        this.cart = cart;
    }

    public ArtworkJpaEntity getArtworkJpaEntity() {
        return artworkJpaEntity;
    }

    public void setArtworkJpaEntity(ArtworkJpaEntity artworkJpaEntity) {
        this.artworkJpaEntity = artworkJpaEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
