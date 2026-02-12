package es.artyhub.tienda_back.domain.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.BusinessException;
import es.artyhub.tienda_back.domain.model.Cart;
import es.artyhub.tienda_back.domain.model.User;

public class CartMapperTest {
    
    @Nested
    @DisplayName("Test fromCartToCartDto")
    class FromCartToCartDtoTest {



        @Test
        @DisplayName("Test fromCartToCartDto with valid Cart should return CartDto")
        void testFromCartToCartDto_ValidInput() {
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

            Cart cart = new Cart(
                    1L,
                    List.of(),
                    user
            );

            CartDto cartDto = CartMapper.getInstance().fromCartToShoppingCartDto(cart);

            assertAll(
                    () -> assertNotNull(cartDto),
                    () -> assertEquals(1L, cartDto.getId()),
                    () -> assertEquals(user.getId(), cartDto.getUser().getId()));
        }
    }

    @Nested
    @DisplayName("Test fromCartDtoToCart")
    class FromCartDtoToCartTest {



        @Test
        @DisplayName("Test fromCartDtoToCart with valid CartDto should return Cart")
        void testFromCartDtoToCart_ValidInput() {
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

            CartDto cartDto = new CartDto(
                    1L,
                    List.of(),
                    userDto
            );

            Cart cart = CartMapper.getInstance().fromCartDtoToShoppingCart(cartDto);

            assertAll(
                    () -> assertNotNull(cart),
                    () -> assertEquals(1L, cart.getId()),
                    () -> assertEquals(userDto.getId(), cart.getUser().getId()));
        }
    }
}
