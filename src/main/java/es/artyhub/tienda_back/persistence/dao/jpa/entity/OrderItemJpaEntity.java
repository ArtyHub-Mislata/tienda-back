package es.artyhub.tienda_back.persistence.dao.jpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_items")
public class OrderItemJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private OrderJpaEntity order;

    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artwork_id", nullable = false)
    private ArtworkJpaEntity artworkJpaEntity;


    public OrderItemJpaEntity() {
    }

    public OrderItemJpaEntity(Long id, Long quantity, BigDecimal price, ArtworkJpaEntity artworkJpaEntity) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.artworkJpaEntity = artworkJpaEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public OrderJpaEntity getOrder() {
        return order;
    }

    public void setOrder(OrderJpaEntity order) {
        this.order = order;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ArtworkJpaEntity getArtworkJpaEntity() {
        return artworkJpaEntity;
    }

    public void setArtworkJpaEntity(ArtworkJpaEntity artworkJpaEntity) {
        this.artworkJpaEntity = artworkJpaEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemJpaEntity that = (OrderItemJpaEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(quantity, that.quantity) && Objects.equals(order, that.order) && Objects.equals(price, that.price) && Objects.equals(artworkJpaEntity, that.artworkJpaEntity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, order, price, artworkJpaEntity);
    }
}
