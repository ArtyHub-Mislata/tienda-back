package es.artyhub.tienda_back.domain.dto;

import jakarta.validation.constraints.Positive;

public class CartItemDto {

        private Long id;

        @Positive(message = "La cantidad debe ser mayor a 0")
        private Long quantity;

        private ArtworkDto artwork;

    public CartItemDto() {
    }

    public CartItemDto(Long id, Long quantity, ArtworkDto artwork) {
        this.id = id;
        this.quantity = quantity;
        this.artwork = artwork;
    }

    public CartItemDto(Long id, Long quantity) {
        this.id = id;
        this.quantity = quantity;

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

    public ArtworkDto getArtwork() {
        return artwork;
    }

    public void setArtwork(ArtworkDto artwork) {
        this.artwork = artwork;
    }
}
