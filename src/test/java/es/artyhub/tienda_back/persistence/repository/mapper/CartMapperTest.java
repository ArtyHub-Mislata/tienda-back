package es.artyhub.tienda_back.persistence.repository.mapper;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import es.artyhub.tienda_back.domain.dto.CartDto;
import es.artyhub.tienda_back.domain.dto.UserDto;
import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.CartJpaEntity;
import es.artyhub.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;

public class CartMapperTest {
    
    @Nested
    @DisplayName("Test fromCartJpaEntityToCartDto")
    class FromCartJpaEntityToCartDtoTest {

        @Test
        @DisplayName("Test fromCartJpaEntityToCartDto with null CartJpaEntity should return null")
        void testFromCartJpaEntityToCartDto_NullInput() {
            CartDto result = CartMapper.getInstance().fromCartJpaEntityToCartDto(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromCartJpaEntityToCartDto with valid CartJpaEntity should return CartDto")
        void testFromCartJpaEntityToCartDto_ValidInput() {
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
            CartJpaEntity cartJpaEntity = new CartJpaEntity(
                    1L,
                    List.of(),
                    userJpaEntity
            );

            CartDto cartDto = CartMapper.getInstance().fromCartJpaEntityToCartDto(cartJpaEntity);

            assertAll(
                    () -> assertNotNull(cartDto),
                    () -> assertEquals(1L, cartDto.getId()),
                    () -> assertEquals(userJpaEntity.getId(), cartDto.getUserDto().getId()));
        }
    }

    @Nested
    @DisplayName("Test fromCartDtoToCartJpaEntity")
    class FromCartDtoToCartJpaEntityTest {

        @Test
        @DisplayName("Test fromCartDtoToCartJpaEntity with null CartDto should return null")
        void testFromCartDtoToCartJpaEntity_NullInput() {
            CartJpaEntity result = CartMapper.getInstance().fromCartDtoToCartJpaEntity(null);
            assertNull(result);
        }

        @Test
        @DisplayName("Test fromCartDtoToCartJpaEntity with valid CartDto should return CartJpaEntity")
        void testFromCartDtoToCartJpaEntity_ValidInput() {
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

            CartJpaEntity cartJpaEntity = CartMapper.getInstance().fromCartDtoToCartJpaEntity(cartDto);

            assertAll(
                    () -> assertNotNull(cartJpaEntity),
                    () -> assertEquals(1L, cartJpaEntity.getId()),
                    () -> assertEquals(userDto.getId(), cartJpaEntity.getUser().getId()));
        }
    }
}
