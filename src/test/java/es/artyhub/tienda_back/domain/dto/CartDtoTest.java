package es.artyhub.tienda_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;

public class CartDtoTest {
    
    @Test
    @DisplayName("should create cart dto")
    public void shouldCreateCartDto() {
        UserDto userDto = new UserDto(
            1L,
            "Name",
            "Email",
            "Password",
            "Description",
            "Address",
            "ImageProfile",
            UserRole.ADMIN
        );
        CartDto cartDto = new CartDto(
            1L,
            List.of(),
            userDto
        );

        assertDoesNotThrow(() -> DtoValidator.validate(cartDto));
    }

    static Stream<CartDto> invalidValues() {
        return Stream.of(
            new CartDto(1L, null, new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "ImageProfile", UserRole.ADMIN)),
            new CartDto(1L, List.of(), null),
            new CartDto(1L, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when cart dto is invalid")
    public void shouldThrowExceptionWhenCartDtoIsInvalid(CartDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
