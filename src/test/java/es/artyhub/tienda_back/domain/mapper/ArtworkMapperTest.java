package es.artyhub.tienda_back.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Artwork;

class ArtworkMapperTest {
    
    @Nested
    @DisplayName("From Artwork to ArtworkDto")
    class FromArtworkToArtworkDto {

        @Test
        @DisplayName("Should return ArtworkDto")
        void shouldReturnArtworkDto() {
            Artwork artwork = new Artwork(
                1L,
                "Artwork",
                "Description",
                "Image",
                1.0,
                1,
                new CategoryDto(1L, "Category")
            );

            ArtworkDto artworkDto = ArtworkMapper.getInstance().fromArtworkToArtworkDto(artwork);

            assertAll(
                () -> assertNotNull(artworkDto, "Mapped Artwork should not be null"),
                () -> assertEquals(artwork.getId(), artworkDto.id()),
                () -> assertEquals(artwork.getName(), artworkDto.name()),
                () -> assertEquals(artwork.getDescription(), artworkDto.description()),
                () -> assertEquals(artwork.getImageUrl(), artworkDto.image()),
                () -> assertEquals(artwork.getPrice(), artworkDto.price()),
                () -> assertEquals(artwork.getStock(), artworkDto.stock()),
                () -> assertEquals(artwork.getCategoryDto(), artworkDto.categoryDto())
            );
        }
    
        @Test
        @DisplayName("When artwork is null should throw BusinessException")
        void whenArtworkIsNull_ShouldThrowBusinessException() {
            Artwork artwork = null;
            
            assertThrows(BusinessException.class, () -> ArtworkMapper.getInstance().fromArtworkToArtworkDto(artwork));
        }
    }

    @Nested
    @DisplayName("From ArtworkDto to Artwork")
    class FromArtworkDtoToArtwork {

        @Test
        @DisplayName("Should return Artwork")
        void shouldReturnArtwork() {
            ArtworkDto artworkDto = new ArtworkDto(
                1L,
                "Artwork",
                "Description",
                "Image",
                1.0,
                1,
                new CategoryDto(1L, "Category")
            );

            Artwork artwork = ArtworkMapper.getInstance().fromArtworkDtoToArtwork(artworkDto);

            assertAll(
                () -> assertNotNull(artwork, "Mapped Artwork should not be null"),
                () -> assertEquals(artworkDto.id(), artwork.getId()),
                () -> assertEquals(artworkDto.name(), artwork.getName()),
                () -> assertEquals(artworkDto.description(), artwork.getDescription()),
                () -> assertEquals(artworkDto.image(), artwork.getImageUrl()),
                () -> assertEquals(artworkDto.price(), artwork.getPrice()),
                () -> assertEquals(artworkDto.stock(), artwork.getStock()),
                () -> assertEquals(artworkDto.categoryDto(), artwork.getCategoryDto())
            );
        }
    
        @Test
        @DisplayName("When artworkDto is null should throw BusinessException")
        void whenArtworkDtoIsNull_ShouldThrowBusinessException() {
            ArtworkDto artworkDto = null;
            
            assertThrows(BusinessException.class, () -> ArtworkMapper.getInstance().fromArtworkDtoToArtwork(artworkDto));
        }
    }
}
