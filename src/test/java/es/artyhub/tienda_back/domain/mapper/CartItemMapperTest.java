package es.artyhub.tienda_back.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.ArtworkDto;
import es.artyhub.tienda_back.domain.dto.CartItemDto;
import es.artyhub.tienda_back.domain.dto.CategoryDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.model.Artwork;
import es.artyhub.tienda_back.domain.model.CartItem;
import es.artyhub.tienda_back.domain.model.Category;
import es.artyhub.tienda_back.domain.model.User;

public class CartItemMapperTest {
    
    @Nested
    @DisplayName("Test fromCartItemToCartItemDto")
    class FromCartItemToCartItemDtoTest {



        @Test
        @DisplayName("Test fromCartItemToCartItemDto with valid CartItem should return CartItemDto")
        void testFromCartItemToCartItemDto_ValidInput() {
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
            CartItem cartItem = new CartItem(
                    1L,
                    10L,
                    artwork
            );

            CartItemDto cartItemDto = CartItemMapper.getInstance().fromCartItemToCartItemDto(cartItem);

            assertAll(
                    () -> assertNotNull(cartItemDto),
                    () -> assertEquals(1L, cartItemDto.getId()),
                    () -> assertEquals(10L, cartItemDto.getQuantity()),
                    () -> assertEquals(artwork.getId(), cartItemDto.getArtwork().getId()),
                    () -> assertEquals("Artwork", cartItemDto.getArtwork().getName()),
                    () -> assertEquals("Description", cartItemDto.getArtwork().getDescription()),
                    () -> assertEquals("Image", cartItemDto.getArtwork().getImage()),
                    () -> assertEquals(BigDecimal.valueOf(10.0), cartItemDto.getArtwork().getPrice()),
                    () -> assertEquals(category.getId(), cartItemDto.getArtwork().getCategoryDto().getId()),
                    () -> assertEquals(user.getId(), cartItemDto.getArtwork().getUserDto().getId()),
                    () -> assertEquals(10L, cartItemDto.getArtwork().getStock()));
        }
    }

    @Nested
    @DisplayName("Test fromCartItemDtoToCartItem")
    class FromCartItemDtoToCartItemTest {



        @Test
        @DisplayName("Test fromCartItemDtoToCartItem with valid CartItemDto should return CartItem")
        void testFromCartItemDtoToCartItem_ValidInput() {
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
            CartItemDto cartItemDto = new CartItemDto(
                    1L,
                    10L,
                    artworkDto
            );

            CartItem cartItem = CartItemMapper.getInstance().fromCartItemDtoToCartItem(cartItemDto);

            assertAll(
                    () -> assertNotNull(cartItem),
                    () -> assertEquals(1L, cartItem.getId()),
                    () -> assertEquals(10L, cartItem.getQuantity()),
                    () -> assertEquals(artworkDto.getId(), cartItem.getArtwork().getId()),
                    () -> assertEquals("Artwork", cartItem.getArtwork().getName()),
                    () -> assertEquals("Description", cartItem.getArtwork().getDescription()),
                    () -> assertEquals("Image", cartItem.getArtwork().getImageUrl()),
                    () -> assertEquals(BigDecimal.valueOf(10.0), cartItem.getArtwork().getPrice()),
                    () -> assertEquals(categoryDto.getId(), cartItem.getArtwork().getCategory().getId()),
                    () -> assertEquals(userDto.getId(), cartItem.getArtwork().getUser().getId()),
                    () -> assertEquals(10L, cartItem.getArtwork().getStock()));
        }
    }
}
