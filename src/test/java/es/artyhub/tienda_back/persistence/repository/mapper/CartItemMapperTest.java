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
import es.artyhub.tienda_back.domain.dto.CartItemDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.ArtworkJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartItemJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

public class CartItemMapperTest {
    
    @Nested
    @DisplayName("Test fromCartItemJpaEntityToCartItemDto")
    class FromCartItemJpaEntityToCartItemDtoTest {

        @Test
        @DisplayName("Test fromCartItemJpaEntityToCartItemDto with null CartItemJpaEntity should return null")
        void testFromCartItemJpaEntityToCartItemDto_NullInput() {
            CartItemDto result = CartItemMapper.getInstance().fromCartItemJpaEntityToCartItemDto(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromCartItemJpaEntityToCartItemDto with valid CartItemJpaEntity should return CartItemDto")
        void testFromCartItemJpaEntityToCartItemDto_ValidInput() {
            ArtworkJpaEntity artworkJpaEntity = new ArtworkJpaEntity(
                    1L,
                    "Artwork",
                    "Description",
                    "Image",
                    BigDecimal.valueOf(10.0),
                    new CategoryJpaEntity(
                            1L,
                            "Category"
                    ),
                    new UserJpaEntity(
                            1L,
                            "User",
                            "Description",
                            "Image",
                            "Email",
                            "Password",
                            "Address",
                            UserRole.USER
                    ),
                    10L
            );
            CartItemJpaEntity cartItemJpaEntity = new CartItemJpaEntity(
                    1L,
                    10L,
                    artworkJpaEntity
            );

            CartItemDto cartItemDto = CartItemMapper.getInstance().fromCartItemJpaEntityToCartItemDto(cartItemJpaEntity);

            assertAll(
                    () -> assertNotNull(cartItemDto),
                    () -> assertEquals(1L, cartItemDto.getId()),
                    () -> assertEquals(10L, cartItemDto.getQuantity()),
                    () -> assertEquals("Description", cartItemDto.getArtwork().getDescription()),
                    () -> assertEquals("Image", cartItemDto.getArtwork().getImage()),
                    () -> assertEquals(BigDecimal.valueOf(10.0), cartItemDto.getArtwork().getPrice()),
                    () -> assertEquals("Category", cartItemDto.getArtwork().getCategoryDto().getName()),
                    () -> assertEquals("User", cartItemDto.getArtwork().getUserDto().getName()),
                    () -> assertEquals(10L, cartItemDto.getArtwork().getStock()));
        }
    }

    @Nested
    @DisplayName("Test fromCartItemDtoToCartItemJpaEntity")
    class FromCartItemDtoToCartItemJpaEntityTest {

        @Test
        @DisplayName("Test fromCartItemDtoToCartItemJpaEntity with null CartItemDto should return null")
        void testFromCartItemDtoToCartItemJpaEntity_NullInput() {
            CartItemJpaEntity result = CartItemMapper.getInstance().fromCartItemDtoToCartItemJpaEntity(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromCartItemDtoToCartItemJpaEntity with valid CartItemDto should return CartItemJpaEntity")
        void testFromCartItemDtoToCartItemJpaEntity_ValidInput() {
            ArtworkDto artworkDto = new ArtworkDto(
                    1L,
                    "Artwork",
                    "Description",
                    "Image",
                    BigDecimal.valueOf(10.0),
                    new CategoryDto(
                            1L,
                            "Category"
                    ),
                    new UserDto(
                            1L,
                            "User",
                            "Description",
                            "Image",
                            "Email",
                            "Password",
                            "Address",
                            UserRole.USER
                    ),
                    10L
            );
            CartItemDto cartItemDto = new CartItemDto(
                    1L,
                    10L,
                    artworkDto
            );

            CartItemJpaEntity cartItemJpaEntityResult = CartItemMapper.getInstance().fromCartItemDtoToCartItemJpaEntity(cartItemDto);

            assertAll(
                    () -> assertNotNull(cartItemJpaEntityResult),
                    () -> assertEquals(1L, cartItemDto.getId()),
                    () -> assertEquals("Artwork", cartItemDto.getArtwork().getName()),
                    () -> assertEquals("Description", cartItemDto.getArtwork().getDescription()),
                    () -> assertEquals("Image", cartItemDto.getArtwork().getImage()),
                    () -> assertEquals(BigDecimal.valueOf(10.0), cartItemDto.getArtwork().getPrice()),
                    () -> assertEquals("Category", cartItemDto.getArtwork().getCategoryDto().getName()),
                    () -> assertEquals("User", cartItemDto.getArtwork().getUserDto().getName()),
                    () -> assertEquals(10L, cartItemDto.getArtwork().getStock()));
        }
    }
}
