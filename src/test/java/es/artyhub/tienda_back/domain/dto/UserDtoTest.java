package es.artyhub.tienda_back.domain.dto;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;

import es.artyhub.tienda_back.domain.enums.UserRole;
import es.artyhub.tienda_back.domain.exception.ValidationException;
import es.artyhub.tienda_back.domain.validation.DtoValidator;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserDtoTest {
    
    @Test
    @DisplayName("Create UserDto with valid data should not throw ValidationException")
    void createUserDto_WithValidData_ShouldNotThrowValidationException() {
        UserDto userDto = new UserDto(1L, "name", "email", "password", "nAccount", "description", "address", "imageUrl", UserRole.ADMIN);

        assertDoesNotThrow(() -> DtoValidator.validate(userDto));
    }

    static Stream<UserDto> invalidUsers() {
        return Stream.of(
            new UserDto(1L, null, "email", "password", "****************", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "", "email", "password", "****************", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, " ", "email", "password", "****************", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", null, "password", "****************", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "", "password", "****************", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", " ", "password", "****************", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", null, "****************", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "", "****************", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", " ", "****************", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", null, "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", "", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", " ", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", "****", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", "*****************", "description", "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", "****************", null, "address", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", "****************", "description", null, "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", "****************", "description", "", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", "****************", "description", " ", "imageUrl", UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", "****************", "description", "address", null, UserRole.ADMIN),
            new UserDto(1L, "name", "email", "password", "****************", "description", "address", "imageUrl", null)
        );
    }

    @ParameterizedTest
    @MethodSource("invalidUsers")
    @DisplayName("Create UserDto with invalid data should throw ValidationException")
    void createUserDto_WithInvalidData_ShouldThrowValidationException(UserDto userDto) {
        assertThrows(ValidationException.class, () -> DtoValidator.validate(userDto));
    }
}
