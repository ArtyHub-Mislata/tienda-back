package es.artyhub.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_items")
public class CartItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private CartJpaEntity cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artwork_id", nullable = false)
    private ArtworkJpaEntity artworkJpaEntity;

    public CartItemJpaEntity(Long id, Long quantity, ArtworkJpaEntity artworkJpaEntity) {
        this.id = id;
        this.quantity = quantity;
        this.artworkJpaEntity = artworkJpaEntity;
    }

    public CartItemJpaEntity() {
    }

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

    public Long getId() {
        return id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }
}
