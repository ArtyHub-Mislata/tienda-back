package es.artyhub.tienda_back.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {

    private Long id;
    private Artwork artwork;
    private Long quantity;
    private BigDecimal price;

    public OrderItem(Long id, Artwork artwork, Long quantity, BigDecimal price) {
        this.id = id;
        this.artwork = artwork;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id) && Objects.equals(artwork, orderItem.artwork) && Objects.equals(quantity, orderItem.quantity) && Objects.equals(price, orderItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, artwork, quantity, price);
    }
}
