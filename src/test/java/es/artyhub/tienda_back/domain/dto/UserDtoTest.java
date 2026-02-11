package es.artyhub.tienda_back.domain.dto;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;

public class UserDtoTest {
    
    @Test
    @DisplayName("should create user dto")
    public void shouldCreateUserDto() {
        UserDto userDto = new UserDto(
            1L,
            "Name",
            "Email",
            "Password",
            "Description",
            "Address",
            "Image",
            UserRole.ADMIN
        );

        assertDoesNotThrow(() -> DtoValidator.validate(userDto));
    }

    static Stream<UserDto> invalidValues() {
        return Stream.of(
            new UserDto(1L, "", "Email", "Password", "Description", "Address", "Image", UserRole.ADMIN),
            new UserDto(1L, " ", "Email", "Password", "Description", "Address", "Image", UserRole.ADMIN),
            new UserDto(1L, null, "Email", "Password", "Description", "Address", "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", "", "Password", "Description", "Address", "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", " ", "Password", "Description", "Address", "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", null, "Password", "Description", "Address", "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", "Email", "", "Description", "Address", "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", "Email", " ", "Description", "Address", "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", "Email", null, "Description", "Address", "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", "Email", "Password", null, "Address", "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", "Email", "Password", "Description", "", "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", "Email", "Password", "Description", " ", "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", "Email", "Password", "Description", null, "Image", UserRole.ADMIN),
            new UserDto(1L, "Name", "Email", "Password", "Description", "Address", null, UserRole.ADMIN),
            new UserDto(1L, "Name", "Email", "Password", "Description", "Address", "Image", null),
            new UserDto(1L, null, null, null, null, null, null, null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidValues")
    @DisplayName("should throw exception when user dto is invalid")
    public void shouldThrowExceptionWhenUserDtoIsInvalid(UserDto invalidDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(invalidDto));
    }
}
