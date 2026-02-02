package es.artyhub.tienda_back.domain.model;

import java.math.BigDecimal;

public class CartItem {

    private Long id;
    private Long quantity;
    private Artwork artwork;
    public CartItem() {
    }

    public CartItem(Long id, Long quantity, Artwork artwork) {
        this.id = id;
        this.quantity = quantity;
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

    public Artwork getArtwork() {
        return artwork;
    }

    public void setArtwork(Artwork artwork) {
        this.artwork = artwork;
    }
}
