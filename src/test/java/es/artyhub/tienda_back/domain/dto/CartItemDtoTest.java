package es.artyhub.tienda_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;

public class CartItemDtoTest {
    
    @Test
    @DisplayName("should create cart item dto")
    public void shouldCreateCartItemDto() {
        CartItemDto cartItemDto = new CartItemDto(
            1L,
            10L,
            new CartDto(1L, List.of(), new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "ImageProfile", UserRole.ADMIN)),
            new ArtworkDto(1L, "Name", "Description", "Image", new BigDecimal(10), new CategoryDto(1L, "Name"), new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "ImageProfile", UserRole.ADMIN), 10L)
        );

        assertDoesNotThrow(() -> DtoValidator.validate(cartItemDto));
    }

    static Stream<CartItemDto> invalidValues() {
        return Stream.of(
            new CartItemDto(1L, 0L, new CartDto(1L, List.of(), new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "ImageProfile", UserRole.ADMIN)), new ArtworkDto(1L, "Name", "Description", "Image", new BigDecimal(10), new CategoryDto(1L, "Name"), new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "ImageProfile", UserRole.ADMIN), 10L)),
            new CartItemDto(1L, -10L, new CartDto(1L, List.of(), new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "ImageProfile", UserRole.ADMIN)), new ArtworkDto(1L, "Name", "Description", "Image", new BigDecimal(10), new CategoryDto(1L, "Name"), new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "ImageProfile", UserRole.ADMIN), 10L)),
            new CartItemDto(1L, 0L, new CartDto(1L, List.of(), new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "ImageProfile", UserRole.ADMIN)), new ArtworkDto(1L, "Name", "Description", "Image", new BigDecimal(10), new CategoryDto(1L, "Name"), new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "ImageProfile", UserRole.ADMIN), 10L))
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when cart item dto is invalid")
    public void shouldThrowExceptionWhenCartItemDtoIsInvalid(CartItemDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
