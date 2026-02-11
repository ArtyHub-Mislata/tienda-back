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

public class ArtworkMapperTest {
    
    @Nested
    @DisplayName("Test fromArtworkToArtworkDto")
    class FromArtworkToArtworkDtoTest {

        @Test
        @DisplayName("Test fromArtworkToArtworkDto with null Artwork should throw exception")
        void testFromArtworkToArtworkDto_NullInput() {
            assertThrows(BusinessException.class, () -> ArtworkMapper.getInstance().fromArtworkToArtworkDto(null));
        }

        @Test
        @DisplayName("Test fromArtworkToArtworkDto with valid Artwork should return ArtworkDto")
        void testFromArtworkToArtworkDto_ValidInput() {
            Category category = new Category(
                    1L,
                    "Category"
            );
            User user = new User(
                    1L,
                    "User",
                    "Description",
                    "Image",
                    "Email",
                    "Password",
                    "Address",
                    UserRole.USER
            );
            Artwork artwork = new Artwork(
                    1L,
                    "Artwork",
                    "Description",
                    "Image",
                    BigDecimal.valueOf(10.0),
                    category,
                    user,
                    10L
            );

            ArtworkDto artworkDto = ArtworkMapper.getInstance().fromArtworkToArtworkDto(artwork);

            assertAll(
                    () -> assertNotNull(artworkDto),
                    () -> assertEquals(1L, artworkDto.getId()),
                    () -> assertEquals("Artwork", artworkDto.getName()),
                    () -> assertEquals("Description", artworkDto.getDescription()),
                    () -> assertEquals("Image", artworkDto.getImage()),
                    () -> assertEquals(BigDecimal.valueOf(10.0), artworkDto.getPrice()),
                    () -> assertEquals(category.getId(), artworkDto.getCategoryDto().getId()),
                    () -> assertEquals(user.getId(), artworkDto.getUserDto().getId()),
                    () -> assertEquals(10L, artworkDto.getStock()));
        }
    }

    @Nested
    @DisplayName("Test fromArtworkDtoToArtwork")
    class FromArtworkDtoToArtworkTest {

        @Test
        @DisplayName("Test fromArtworkDtoToArtwork with null ArtworkDto should throw exception")
        void testFromArtworkDtoToArtwork_NullInput() {
            assertThrows(BusinessException.class, () -> ArtworkMapper.getInstance().fromArtworkDtoToArtwork(null));
        }

        @Test
        @DisplayName("Test fromArtworkDtoToArtwork with valid ArtworkDto should return Artwork")
        void testFromArtworkDtoToArtwork_ValidInput() {
            CategoryDto categoryDto = new CategoryDto(
                    1L,
                    "Category"
            );
            UserDto userDto = new UserDto(
                    1L,
                    "User",
                    "Description",
                    "Image",
                    "Email",
                    "Password",
                    "Address",
                    UserRole.USER
            );
            ArtworkDto artworkDto = new ArtworkDto(
                    1L,
                    "Artwork",
                    "Description",
                    "Image",
                    BigDecimal.valueOf(10.0),
                    categoryDto,
                    userDto,
                    10L
            );

            Artwork artwork = ArtworkMapper.getInstance().fromArtworkDtoToArtwork(artworkDto);

            assertAll(
                    () -> assertNotNull(artwork),
                    () -> assertEquals(1L, artwork.getId()),
                    () -> assertEquals("Artwork", artwork.getName()),
                    () -> assertEquals("Description", artwork.getDescription()),
                    () -> assertEquals("Image", artwork.getImageUrl()),
                    () -> assertEquals(BigDecimal.valueOf(10.0), artwork.getPrice()),
                    () -> assertEquals(categoryDto.getId(), artwork.getCategory().getId()),
                    () -> assertEquals(userDto.getId(), artwork.getUser().getId()),
                    () -> assertEquals(10L, artwork.getStock()));
        }
    }
}
