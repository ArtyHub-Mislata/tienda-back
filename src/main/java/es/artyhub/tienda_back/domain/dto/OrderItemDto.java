package es.artyhub.tienda_back.domain.dto;

import es.artyhub.tienda_back.domain.model.Artwork;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItemDto {
    private Long id;
    private Long quantity;
    private BigDecimal price;
    private ArtworkDto artwork;

    public OrderItemDto(Long id, Long quantity, BigDecimal price, ArtworkDto artwork) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.artwork = artwork;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ArtworkDto getArtwork() {
        return artwork;
    }

    public void setArtwork(ArtworkDto artwork) {
        this.artwork = artwork;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemDto that = (OrderItemDto) o;
        return Objects.equals(id, that.id) && Objects.equals(quantity, that.quantity) && Objects.equals(price, that.price) && Objects.equals(artwork, that.artwork);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, quantity, price, artwork);
    }
}
