package es.artyhub.tienda_back.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Artwork;
import es.artyhub.tienda_back.domain.model.Category;
import es.artyhub.tienda_back.domain.model.User;

class ArtworkMapperTest {
    
    @Nested
    @DisplayName("From Artwork to ArtworkDto")
    class FromArtworkToArtworkDto {

        @Test
        @DisplayName("Should return ArtworkDto")
        void shouldReturnArtworkDto() {
            Artwork artwork = new Artwork(1L, "Artwork", "Description", "Image", new BigDecimal(1.0), new Category(1L, "Category"), new User(1L, "name", "email", "password", "nAccount", "description", "address", "imageUrl", UserRole.ADMIN));

            ArtworkDto artworkDto = ArtworkMapper.getInstance().fromArtworkToArtworkDto(artwork);

            assertAll(
                () -> assertNotNull(artworkDto, "Mapped Artwork should not be null"),
                () -> assertEquals(artwork.getId(), artworkDto.getId()),
                () -> assertEquals(artwork.getName(), artworkDto.getName()),
                () -> assertEquals(artwork.getDescription(), artworkDto.getDescription()),
                () -> assertEquals(artwork.getImageUrl(), artworkDto.getImage()),
                () -> assertEquals(artwork.getPrice(), artworkDto.getPrice())
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
                new BigDecimal(1.0),
                new CategoryDto(1L, "Category"),
                new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageUrl", UserRole.ADMIN)
            );

            Artwork artwork = ArtworkMapper.getInstance().fromArtworkDtoToArtwork(artworkDto);

            assertAll(
                () -> assertNotNull(artwork, "Mapped Artwork should not be null"),
                () -> assertEquals(artworkDto.getId(), artwork.getId()),
                () -> assertEquals(artworkDto.getName(), artwork.getName()),
                () -> assertEquals(artworkDto.getDescription(), artwork.getDescription()),
                () -> assertEquals(artworkDto.getImage(), artwork.getImageUrl()),
                () -> assertEquals(artworkDto.getPrice(), artwork.getPrice())
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
