package es.artyhub.tienda_back.persistence.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

public class ArtworkMapperTest {
    
    @Nested
    @DisplayName("Test fromArtworkJpaEntityToArtworkDto")
    class FromArtworkJpaEntityToArtworkDtoTest {

        @Test
        @DisplayName("Test fromArtworkJpaEntityToArtworkDto with null ArtworkJpaEntity should return null")
        void testFromArtworkJpaEntityToArtworkDto_NullInput() {
            ArtworkDto result = ArtworkMapper.getInstance().fromArtworkJpaEntityToArtworkDto(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromArtworkJpaEntityToArtworkDto with valid ArtworkJpaEntity should return ArtworkDto")
        void testFromArtworkJpaEntityToArtworkDto_ValidInput() {
            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity(
                    1L,
                    "Category"
            );
            UserJpaEntity userJpaEntity = new UserJpaEntity(
                    1L,
                    "User",
                    "Description",
                    "Image",
                    "Email",
                    "Password",
                    "Address",
                    UserRole.USER
            );
            ArtworkJpaEntity artworkJpaEntity = new ArtworkJpaEntity(
                    1L,
                    "Artwork",
                    "Description",
                    "Image",
                    BigDecimal.valueOf(10.0),
                    categoryJpaEntity,
                    userJpaEntity,
                    10L
            );

            ArtworkDto artworkDto = ArtworkMapper.getInstance().fromArtworkJpaEntityToArtworkDto(artworkJpaEntity);

            assertAll(
                    () -> assertNotNull(artworkDto),
                    () -> assertEquals(1L, artworkDto.getId()),
                    () -> assertEquals("Artwork", artworkDto.getName()),
                    () -> assertEquals("Description", artworkDto.getDescription()),
                    () -> assertEquals("Image", artworkDto.getImage()),
                    () -> assertEquals(BigDecimal.valueOf(10.0), artworkDto.getPrice()),
                    () -> assertEquals(categoryJpaEntity.getId(), artworkDto.getCategoryDto().getId()),
                    () -> assertEquals(userJpaEntity.getId(), artworkDto.getUserDto().getId()),
                    () -> assertEquals(10L, artworkDto.getStock()));
        }
    }

    @Nested
    @DisplayName("Test fromArtworkDtoToArtworkJpaEntity")
    class FromArtworkDtoToArtworkJpaEntityTest {

        @Test
        @DisplayName("Test fromArtworkDtoToArtworkJpaEntity with null ArtworkDto should return null")
        void testFromArtworkDtoToArtworkJpaEntity_NullInput() {
            ArtworkJpaEntity result = ArtworkMapper.getInstance().fromArtworkDtoToArtworkJpaEntity(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromArtworkDtoToArtworkJpaEntity with valid ArtworkDto should return ArtworkJpaEntity")
        void testFromArtworkDtoToArtworkJpaEntity_ValidInput() {
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

            ArtworkJpaEntity artworkJpaEntity = ArtworkMapper.getInstance().fromArtworkDtoToArtworkJpaEntity(artworkDto);

            assertAll(
                    () -> assertNotNull(artworkJpaEntity),
                    () -> assertEquals(1L, artworkJpaEntity.getId()),
                    () -> assertEquals("Artwork", artworkJpaEntity.getName()),
                    () -> assertEquals("Description", artworkJpaEntity.getDescription()),
                    () -> assertEquals("Image", artworkJpaEntity.getImageUrl()),
                    () -> assertEquals(BigDecimal.valueOf(10.0), artworkJpaEntity.getPrice()),
                    () -> assertEquals(categoryDto.getId(), artworkJpaEntity.getCategory().getId()),
                    () -> assertEquals(userDto.getId(), artworkJpaEntity.getUserJpaEntity().getId()),
                    () -> assertEquals(10L, artworkJpaEntity.getStock()));
        }
    }
}
