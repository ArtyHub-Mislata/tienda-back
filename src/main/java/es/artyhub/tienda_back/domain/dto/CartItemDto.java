package es.artyhub.tienda_back.domain.dto;

import es.artyhub.tienda_back.domain.model.Artwork;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public class CartItemDto {

        private Long id;

        @Positive(message = "La cantidad debe ser mayor a 0")
        private Long quantity;

        private ArtworkDto artworkDto;

    public CartItemDto() {
    }

    public CartItemDto(Long id, Long quantity, ArtworkDto artworkDto) {
        this.id = id;
        this.quantity = quantity;
        this.artworkDto = artworkDto;
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

    public ArtworkDto getArtworkDto() {
        return artworkDto;
    }

    public void setArtworkDto(ArtworkDto artworkDto) {
        this.artworkDto = artworkDto;
    }
}
